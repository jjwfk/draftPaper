package com.daitao.draftPaper.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * root页面
 */
public class RootSwing {
    private static JFrame frame;

    /**
     * {
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public void createAndShowGUI() throws IOException {
        frame = createJFrame();
        createLayout(frame);
        new MenuHeaderSwing().createMenu(frame);
        JTextArea contentShow = new ContentSwing().createContentShow(frame);
        new TableOfContentsSwing().createTableOfContents(frame, contentShow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JFrame createJFrame() {
        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) Math.round(dimension.width * 0.618);
        int height = (int) Math.round(width * 0.618);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static void createLayout(JFrame frame) {
        //BorderLayout 布局
        frame.setLayout(new BorderLayout());
    }
}
