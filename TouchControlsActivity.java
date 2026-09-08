package com.chatppss4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class TouchControlsActivity extends View {

    // === D-Pad (Cruzeta) ===
    private Drawable dpad;
    private float dpadX, dpadY;
    private static final float DPAD_SIZE = 200f;

    // === Botones de Acción ===
    private Drawable btnTriangle, btnCircle, btnSquare, btnCross, btnPS;
    private static final float BTN_SIZE = 90f;

    // === Botones L1 L2 R1 R2 ===
    private Drawable btnL1, btnL2, btnR1, btnR2;
    private static final float TRIGGER_W = 120f;
    private static final float TRIGGER_H = 70f;

    // === Stick Analógico ===
    private Drawable stickBase, stickCenter;
    private float stickBaseX, stickBaseY;
    private PointF stickPos = new PointF(0,0);
    private boolean stickPressed = false;
    private static final float STICK_BASE = 160f;
    private static final float STICK_CENTER = 60f;
    private static final float STICK_RADIUS = 60f;

    // === Panel Táctil de PS4 ===
    private Drawable panelTactil;
    private float panelX, panelY;
    private static final float PANEL_W = 350f;
    private static final float PANEL_H = 50f;

    public TouchControlsActivity(Context context) {
        super(context);
        init(context);
    }

    public TouchControlsActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        setLayerType(LAYER_TYPE_HARDWARE, null);

        // === CARGAR TUS IMÁGENES ===
        dpad = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_dpad, null);

        btnTriangle = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_triangle, null);
        btnCircle = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_circle, null);
        btnSquare = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_square, null);
        btnCross = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_cross, null);
        btnPS = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_ps, null);

        btnL1 = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_L1, null);
        btnL2 = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_L2, null);
        btnR1 = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_R1, null);
        btnR2 = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_R2, null);

        stickBase = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_stick_base, null);
        stickCenter = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_stick_centro, null);

        panelTactil = ResourcesCompat.getDrawable(ctx.getResources(), R.drawable.btn_panel_tactil, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Posiciones relativas a la pantalla
        dpadX = w * 0.15f;
        dpadY = h * 0.65f;

        stickBaseX = w * 0.20f;
        stickBaseY = h * 0.85f;

        // Panel táctil — arriba, centrado (igual que mando de PS4)
        panelX = (w - PANEL_W) / 2f;
        panelY = h * 0.08f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // === PANEL TÁCTIL ===
        if (panelTactil != null) {
            panelTactil.setBounds(
                (int)panelX, (int)panelY,
                (int)(panelX + PANEL_W), (int)(panelY + PANEL_H)
            );
            panelTactil.draw(canvas);
        }

        // === D-PAD ===
        if (dpad != null) {
            dpad.setBounds(
                (int)(dpadX - DPAD_SIZE/2),
                (int)(dpadY - DPAD_SIZE/2),
                (int)(dpadX + DPAD_SIZE/2),
                (int)(dpadY + DPAD_SIZE/2)
            );
            dpad.draw(canvas);
        }

        // === STICK ANALÓGICO ===
        if (stickBase != null) {
            stickBase.setBounds(
                (int)(stickBaseX - STICK_BASE/2),
                (int)(stickBaseY - STICK_BASE/2),
                (int)(stickBaseX + STICK_BASE/2),
                (int)(stickBaseY + STICK_BASE/2)
            );
            stickBase.draw(canvas);
        }
        if (stickCenter != null) {
            float cx = stickBaseX + stickPos.x * STICK_RADIUS;
            float cy = stickBaseY + stickPos.y * STICK_RADIUS;
            stickCenter.setBounds(
                (int)(cx - STICK_CENTER/2),
                (int)(cy - STICK_CENTER/2),
                (int)(cx + STICK_CENTER/2),
                (int)(cy + STICK_CENTER/2)
            );
            stickCenter.draw(canvas);
        }

        // === BOTONES L1 L2 (Arriba Izquierda) ===
        if (btnL2 != null) {
            btnL2.setBounds(50, 30, (int)(50+TRIGGER_W), (int)(30+TRIGGER_H));
            btnL2.draw(canvas);
        }
        if (btnL1 != null) {
            btnL1.setBounds(50, 100, (int)(50+TRIGGER_W), (int)(100+TRIGGER_H));
            btnL1.draw(canvas);
        }

        // === BOTONES R1 R2 (Arriba Derecha) ===
        float rX = getWidth() - 50 - TRIGGER_W;
        if (btnR2 != null) {
            btnR2.setBounds((int)rX, 30, (int)(rX+TRIGGER_W), (int)(30+TRIGGER_H));
            btnR2.draw(canvas);
        }
        if (btnR1 != null) {
            btnR1.setBounds((int)rX, 100, (int)(rX+TRIGGER_W), (int)(100+TRIGGER_H));
            btnR1.draw(canvas);
        }

        // === BOTONES DE ACCIÓN (Derecha - Estilo PlayStation) ===
        float cx = getWidth() - 160f;
        float cy = getHeight() * 0.70f;
        float gap = BTN_SIZE * 1.3f;

        // Triángulo △ — Arriba
        if (btnTriangle != null) {
            btnTriangle.setBounds(
                (int)(cx - BTN_SIZE/2), (int)(cy - gap - BTN_SIZE/2),
                (int)(cx + BTN_SIZE/2), (int)(cy - gap + BTN_SIZE/2)
            );
            btnTriangle.draw(canvas);
        }
        // Círculo ○ — Derecha
        if (btnCircle != null) {
            btnCircle.setBounds(
                (int)(cx + gap - BTN_SIZE/2), (int)(cy - BTN_SIZE/2),
                (int)(cx + gap + BTN_SIZE/2), (int)(cy + BTN_SIZE/2)
            );
            btnCircle.draw(canvas);
        }
        // Cruz × — Abajo
        if (btnCross != null) {
            btnCross.setBounds(
                (int)(cx - BTN_SIZE/2), (int)(cy + gap - BTN_SIZE/2),
                (int)(cx + BTN_SIZE/2), (int)(cy + gap + BTN_SIZE/2)
            );
            btnCross.draw(canvas);
        }
        // Cuadrado □ — Izquierda
        if (btnSquare != null) {
            btnSquare.setBounds(
                (int)(cx - gap - BTN_SIZE/2), (int)(cy - BTN_SIZE/2),
                (int)(cx - gap + BTN_SIZE/2), (int)(cy + BTN_SIZE/2)
            );
            btnSquare.draw(canvas);
        }
        // Botón PS — Centro abajo
        if (btnPS != null) {
            btnPS.setBounds(
                (int)(cx - BTN_SIZE/2), (int)(cy + gap*2 - BTN_SIZE/2),
                (int)(cx + BTN_SIZE/2), (int)(cy + gap*2 + BTN_SIZE/2)
            );
            btnPS.draw(canvas);
        }
    }

    // === DETECTAR TOQUE EN EL PANEL TÁCTIL ===
    private boolean tocaPanel(float x, float y) {
        return x >= panelX && x <= panelX + PANEL_W &&
               y >= panelY && y <= panelY + PANEL_H;
    }

    // === TOUCH — Panel táctil + Stick ===
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getActionMasked();

        // === PANEL TÁCTIL ===
        if (tocaPanel(x, y)) {
            if (action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_MOVE ||
                action == MotionEvent.ACTION_UP) {
                return true;
            }
        }

        // === STICK ANALÓGICO ===
        float dx = x - stickBaseX;
        float dy = y - stickBaseY;
        float dist = (float)Math.hypot(dx, dy);

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            if (dist < STICK_BASE || stickPressed) {
                stickPressed = true;
                if (dist > STICK_RADIUS) {
                    dx *= STICK_RADIUS / dist;
                    dy *= STICK_RADIUS / dist;
                }
                stickPos.set(dx / STICK_RADIUS, dy / STICK_RADIUS);
                invalidate();
                return true;
            }
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            stickPressed = false;
            stickPos.set(0, 0);
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}

