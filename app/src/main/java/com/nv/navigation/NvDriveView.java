package com.nv.navigation;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import java.util.*;

public class NvDriveView extends View {
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path path = new Path();
    private final Typeface bold = Typeface.create("sans", Typeface.BOLD);
    private final Typeface normal = Typeface.create("sans", Typeface.NORMAL);

    public NvDriveView(Context c) {
        super(c);
        p.setStrokeCap(Paint.Cap.ROUND);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void txt(Canvas c, String s, float x, float y, float size, int color, boolean b) {
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        p.setTextSize(size);
        p.setTypeface(b ? bold : normal);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText(s, x, y, p);
    }

    private void rr(Canvas c, float l, float t, float r, float b, float rad, int color) {
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        c.drawRoundRect(l,t,r,b,rad,rad,p);
    }

    private void strokeRR(Canvas c, float l, float t, float r, float b, float rad, int color, float sw) {
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(sw);
        p.setColor(color);
        c.drawRoundRect(l,t,r,b,rad,rad,p);
        p.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        float w = getWidth(), h = getHeight();

        LinearGradient g = new LinearGradient(0,0,0,h,
                new int[]{0xFF527985,0xFF365866,0xFF23414E,0xFF162E3A},
                null, Shader.TileMode.CLAMP);
        p.setShader(g);
        c.drawRect(0,0,w,h,p);
        p.setShader(null);

        p.setStrokeWidth(18);
        p.setColor(0xFF627E86);
        float[][] roads = {
                {0.05f,0.22f,0.95f,0.76f},
                {0.10f,0.83f,0.84f,0.13f},
                {0.00f,0.55f,1.00f,0.47f},
                {0.20f,0.08f,0.67f,0.96f}
        };
        for(float[] r: roads){
            c.drawLine(w*r[0],h*r[1],w*r[2],h*r[3],p);
        }
        p.setStrokeWidth(3);
        p.setColor(0xFF9BB0B5);
        for(float[] r: roads){
            c.drawLine(w*r[0],h*r[1],w*r[2],h*r[3],p);
        }

        path.reset();
        path.moveTo(w*.49f,h*.88f);
        path.cubicTo(w*.50f,h*.76f,w*.47f,h*.66f,w*.44f,h*.58f);
        path.cubicTo(w*.40f,h*.49f,w*.37f,h*.41f,w*.31f,h*.32f);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(0x5518C8FF); p.setStrokeWidth(38); c.drawPath(path,p);
        p.setColor(0xFF007CFF); p.setStrokeWidth(22); c.drawPath(path,p);
        p.setColor(0xFF41E5FF); p.setStrokeWidth(8); c.drawPath(path,p);
        p.setStyle(Paint.Style.FILL);

        rr(c, w*.05f, 22, w*.95f, 88, 28, 0xE80B2A44);
        txt(c,"نام مکان یا NV Code را جستجو کنید",w*.53f,65,18,Color.WHITE,false);
        txt(c,"×",w*.10f,65,28,Color.WHITE,true);

        float hudL=w*.26f, hudR=w*.74f, hudT=105, hudB=185;
        rr(c,hudL,hudT,hudR,hudB,24,0xE0105B57);
        strokeRR(c,hudL,hudT,hudR,hudB,24,0xFF79F6D8,3);
        txt(c,"↱  ۵۰۰ متر",w*.50f,143,25,Color.WHITE,true);
        txt(c,"خروجی بعدی",w*.50f,169,15,Color.WHITE,false);

        String[] icons={"لایه‌ها","سوخت","غذا","دیدنی"};
        for(int i=0;i<icons.length;i++){
            float top=230+i*72;
            rr(c,12,top,82,top+60,18,0xDD0B2B45);
            txt(c,icons[i],47,top+37,12,Color.WHITE,false);
        }

        rr(c,w-150,260,w-12,322,18,0xDD0B2B45);
        txt(c,"☁ آب‌وهوا",w-81,284,14,Color.WHITE,true);
        txt(c,"قم • بدون هشدار",w-81,306,11,0xFFB8C7D2,false);

        rr(c,w-150,334,w-12,396,18,0xDD0B2B45);
        txt(c,"★ دیدنی جلوتر",w-81,358,14,Color.WHITE,true);
        txt(c,"برج آزادی • ۸۰۰م",w-81,380,11,0xFFB8C7D2,false);

        p.setStyle(Paint.Style.FILL); p.setColor(0xE8082639);
        c.drawCircle(66,h-194,48,p);
        p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(4); p.setColor(0xFF62E881);
        c.drawCircle(66,h-194,46,p); p.setStyle(Paint.Style.FILL);
        txt(c,"۹۲",66,h-196,30,0xFF62E881,true);
        txt(c,"km/h",66,h-174,12,Color.WHITE,false);

        p.setColor(Color.WHITE); c.drawCircle(132,h-190,28,p);
        p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(6); p.setColor(0xFFFF586B);
        c.drawCircle(132,h-190,25,p); p.setStyle(Paint.Style.FILL);
        txt(c,"100",132,h-184,15,Color.BLACK,true);

        rr(c,10,h-150,w-10,h-88,20,0xEE0B2A44);
        txt(c,"۱۰:۳۶",w*.15f,h-118,16,Color.WHITE,true);
        txt(c,"۲۸ دقیقه",w*.38f,h-118,16,Color.WHITE,true);
        txt(c,"۱۴ km",w*.61f,h-118,16,Color.WHITE,true);
        txt(c,"برج آزادی",w*.84f,h-118,15,Color.WHITE,true);
        txt(c,"مسیرها",w*.84f,h-99,11,0xFF18C8FF,false);

        rr(c,8,h-78,w-8,h-10,24,0xF407243B);
        String[] nav={"مسیریابی","جستجو","علاقه‌مندی","مسیرها","آب‌وهوا","تنظیمات"};
        for(int i=0;i<nav.length;i++){
            float x=(i+.5f)*w/6f;
            txt(c,nav[i],x,h-34,10,i==0?0xFF18C8FF:Color.WHITE,i==0);
        }
    }
}
