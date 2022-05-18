/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanButton;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hamou
 */
public class base {
     Resources theme = UIManager.initFirstTheme("/theme");
      Form base = new Form(BoxLayout.y());
      
      SpanButton sbtn = new SpanButton("Compte");
       public base(Resources res) {
           sbtn.addActionListener(ev ->
          {
           //   new GererProfil().show();
              
          });
            base.add(sbtn);
       }
        public void show() {
        base.show();
    }
}
