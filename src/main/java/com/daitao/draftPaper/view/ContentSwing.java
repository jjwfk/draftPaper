package com.daitao.draftPaper.view;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

/**
 * 内容显示
 */
public class ContentSwing {
    public JTextArea createContentShow(JFrame jFrame) {
        //创建一个JPanel对象
        JTextArea jta = new JTextArea("请输入内容", 7, 30);
        //jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色
        jta.setFont(new Font("楷体", Font.BOLD, 16));    //修改字体样式
        JScrollPane jsp = new JScrollPane(jta);    //将文本域放入滚动窗口
        Dimension size = jta.getPreferredSize();    //获得文本域的首选大小
        jsp.setBounds(110, 90, size.width, size.height);
        jFrame.add(jsp, BorderLayout.CENTER);
        return jta;
    }
}
