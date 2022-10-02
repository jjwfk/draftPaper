package com.daitao.draftPaper;

import com.daitao.draftPaper.view.RootSwing;

import javax.swing.*;
import java.io.IOException;

/**
 * 启动类
 */
public class AppStart {
    public static void main(String[] args) {
        // 显示应用 GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RootSwing().createAndShowGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
