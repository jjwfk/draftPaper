package com.daitao.draftPaper.view;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.*;

/**
 * 菜单栏
 */
public class MenuHeaderSwing {
    public void createMenu(JFrame frame) {
        /*
         * 创建一个菜单栏
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu viewMenu = new JMenu("视图");
        JMenu configMenu = new JMenu("配置");
        JMenu aboutMenu = new JMenu("关于");

        JMenuItem newMenuItem = new JMenuItem("新建工作空间(Ctrl+N)");
        JMenu openMenuItem = new JMenu("打开工作空间(Ctrl+O)");
        openMenuItem.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println(1);
                try {
                    clientOpenMenu(openMenuItem);
                } catch (IOException ex) {

                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        JMenuItem saveMenuItem = new JMenuItem("保存工作空间(Ctrl+S)");
        // 子菜单添加到一级菜单
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveMenuItem);

        JMenuItem jMenuItem = new JMenuItem("撤销(U)                         Ctrl+Z");
        JMenuItem tMenuItem = new JMenuItem("剪切(T)                         Ctrl+X");
        JMenuItem cMenuItem = new JMenuItem("复制(C)                         Ctrl+C");
        JMenuItem pMenuItem = new JMenuItem("粘贴(P)                         Ctrl+V");
        JMenuItem lMenuItem = new JMenuItem("删除(L)                             Del");
        JMenuItem fMenuItem = new JMenuItem("查找(F)                         Ctrl+F");
        JMenuItem nMenuItem = new JMenuItem("查找下一个(N)                    F3");
        JMenuItem rMenuItem = new JMenuItem("替换(R)                         Ctrl+H");
        JMenuItem gMenuItem = new JMenuItem("转到(G)                         Ctrl+G");
        JMenuItem aMenuItem = new JMenuItem("全选(A)                         Ctrl+A");
        // 子菜单添加到一级菜单
        editMenu.add(jMenuItem);
        editMenu.addSeparator();
        editMenu.add(tMenuItem);
        editMenu.add(cMenuItem);
        editMenu.add(pMenuItem);
        editMenu.add(lMenuItem);
        editMenu.addSeparator();
        editMenu.add(fMenuItem);
        editMenu.add(nMenuItem);
        editMenu.add(rMenuItem);
        editMenu.add(gMenuItem);
        editMenu.addSeparator();
        editMenu.add(aMenuItem);

        JMenuItem simpleViewItem = new JMenuItem("简单视图");
        JMenuItem detailedViewItem = new JMenuItem("详细视图");
        // 子菜单添加到一级菜单
        viewMenu.add(simpleViewItem);
        fileMenu.addSeparator();
        viewMenu.add(detailedViewItem);

        JMenuItem setItem = new JMenuItem("设置");
        configMenu.add(setItem);

        JMenuItem appDetailsItem = new JMenuItem("软件详情");
        aboutMenu.add(appDetailsItem);

        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(configMenu);
        menuBar.add(aboutMenu);

        frame.add(menuBar, BorderLayout.NORTH);
    }

    private void clientOpenMenu(JMenu jMenu) throws IOException {
        //二级标题点击事件
        jMenu.removeAll();
        File file = new File("/Users/daitao/Documents/Demo/draftPaper/draftPaper/src/main/resources/config/workHome.txt");
        if (!file.exists() || !file.isFile()) {
            JMenuItem simpleViewItem = new JMenuItem("...");
            jMenu.add(simpleViewItem);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(":");
            if ("workHomePath".equals(split[0])) {
                String workHomePath = split[1];
                System.out.println(workHomePath);
                JMenuItem simpleViewItem = new JMenuItem(workHomePath);
                jMenu.add(simpleViewItem);
            }
        }
    }
}
