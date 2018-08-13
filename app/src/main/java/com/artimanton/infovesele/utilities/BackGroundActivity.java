package com.artimanton.infovesele.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ScrollView;

import com.artimanton.infovesele.R;

public class BackGroundActivity {
    public static void setBackground(Context context, Activity activity){
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprinkles); // берем картинку из ресурса
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT); // гшоворим обьекту как рисовать (у меня это повторяющийся фон)
        ScrollView layout = activity.findViewById(R.id.myview);
        layout.setBackgroundDrawable(bitmapDrawable); // задаём фон нашему лэйауту
    }
}
