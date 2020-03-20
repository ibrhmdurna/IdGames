package com.ibrhmdurna.idgames.util;

import android.widget.RelativeLayout;

public interface CardAdapter {
    float getBaseElevation();
    RelativeLayout getRootLayout(int position);
    int getCount();
}
