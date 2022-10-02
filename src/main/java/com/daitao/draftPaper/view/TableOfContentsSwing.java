package com.daitao.draftPaper.view;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

/**
 * 左侧边栏
 */
public class TableOfContentsSwing {

    public void createTableOfContents(JFrame frame, JTextArea jTextArea) throws IOException {
        File file = new File("/Users/daitao/Documents/Demo/draftPaper/draftPaper/src/main/resources/config/workHome.txt");
        if (!file.exists() || !file.isFile()) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        String newPath = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(":");
            if ("workHomePath".equals(split[0])) {
                newPath = line;
            }
        }
        if (newPath == null || "".equals(newPath)) {
            return;
        }
        String path = newPath.split(":")[1];
        File rootFile = new File(path);
        // 递归构建文件树的根节点
        DefaultMutableTreeNode rootNode = buildFileTree(rootFile);
        // 使用根节点去初始化一颗树
        JTree fileBrowserTree = new JTree(rootNode);
        fileBrowserTree.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(fileBrowserTree);
        jScrollPane.setBorder(null);
        jScrollPane.setPreferredSize(new Dimension((int) Math.round(frame.getWidth() * 0.2), frame.getHeight()));
        // 为 fileBrowserTree 添加滚动条，再将其添加到 fileBrowserFrame
        frame.add(jScrollPane, BorderLayout.WEST);
        fileBrowserTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                String filePath = path;
                for (int i = 1; i < e.getNewLeadSelectionPath().getPath().length; i++) {
                    if (e.getNewLeadSelectionPath().getPath()[i] == null) {
                        return;
                    }
                    filePath = filePath + "/" + e.getNewLeadSelectionPath().getPath()[i].toString();
                }
                try {
                    createFileShow(jTextArea, filePath);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createFileShow(JTextArea jTextArea, String path) throws IOException {
        System.out.println(path);
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        jTextArea.read(reader, null);
        reader.close();
    }

    private DefaultMutableTreeNode buildFileTree(File rootFile) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootFile.getName());
        // 如果是文件，即没有子文件
        if (rootFile.isFile()) {
            return rootNode;
        }
        File[] subFiles = rootFile.listFiles();
        // 列出文件出错，一般不会发生
        if (subFiles == null) {
            return rootNode;
        }
        for (File subFile : subFiles) {
            // 如果 subFile 是文件，则新建一个树节点
            if (subFile.isFile()) {
                DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(subFile.getName());
                rootNode.add(subNode);
            }
            // 如果 subFile 是目录，那么构建一颗子树
            if (subFile.isDirectory()) {
                DefaultMutableTreeNode subNode = buildFileTree(subFile);
                rootNode.add(subNode);
            }
        }
        return rootNode;
    }
}
