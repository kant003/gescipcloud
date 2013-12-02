package com.angel.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class OneWidgetLayoutPanel extends LayoutPanel implements AcceptsOneWidget {
        private IsWidget widget = null;
        
        @Override
        public void setWidget(IsWidget w) {
                if( widget != null) super.remove(widget);
                widget = w;
                if(w != null) super.add(w);
        }
}