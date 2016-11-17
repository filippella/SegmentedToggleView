package org.dalol.segmentedcontrol;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/17/2016
 */
public class SegmentedToggleView extends RadioGroup {

    private OnUIToggleCheckChangeListener mChangeListener;

    public SegmentedToggleView(Context context) {
        this(context, null);
    }

    public SegmentedToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.segmented_control_container_bg));
    }

    public void setListItems(List<String> items) {
        Context context = getContext();

        int height = getCustomSize(50);
        int padding = getCustomSize(8);

        int size = items.size();

        for (int i = 0; i < size; i++) {
            RadioButton button = (RadioButton) inflate(context, R.layout.segmented_control_layout, null);
            button.setText(items.get(i));
            button.setButtonDrawable(null);
            button.setGravity(Gravity.CENTER);
            button.setPadding(padding, padding, padding, padding);
            button.setMinHeight(height);
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && mChangeListener != null) {
                        mChangeListener.onCheckChanged((RadioButton) buttonView, indexOfChild(buttonView));
                    }
                }
            });
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
            params.weight = 1;
            addView(button, params);
        }
    }

    private int getCustomSize(float size) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics()));
    }

    public void setSelectedIndex(int index) {
        RadioButton buttonView = (RadioButton) getChildAt(index);
        buttonView.setChecked(true);
    }

    public void setChangeListener(OnUIToggleCheckChangeListener listener) {
        mChangeListener = listener;
    }

    public interface OnUIToggleCheckChangeListener {

        void onCheckChanged(RadioButton button, int position);
    }
}