package com.filters.filter.advanced;

import android.opengl.GLES20;

import com.camera.R;
import com.filters.filter.base.gpuimage.GPUImageFilter;
import com.filters.utils.FilterParams;
import com.filters.utils.OpenGlUtils;

/**
 * Created by Administrator on 2016/5/22.
 */
public class BeautyFilter extends GPUImageFilter {
    private int mSingleStepOffsetLocation;
    private int mParamsLocation;

    public BeautyFilter() {
        super(NO_FILTER_VERTEX_SHADER,
                OpenGlUtils.readShaderFromRawResource(R.raw.beauty));
    }

    @Override
    protected void onInit() {
        super.onInit();
        mSingleStepOffsetLocation = GLES20.glGetUniformLocation(getProgram(), "singleStepOffset");
        mParamsLocation = GLES20.glGetUniformLocation(getProgram(), "params");
        setBeautyLevel(FilterParams.beautyLevel);
    }

    private void setTexelSize(final float w, final float h) {
        setFloatVec2(mSingleStepOffsetLocation, new float[]{2.0f / w, 2.0f / h});
    }

    @Override
    public void onInputSizeChanged(final int width, final int height) {
        super.onInputSizeChanged(width, height);
        setTexelSize(width, height);
    }

    public void setBeautyLevel(int level) {
        switch (level) {
            case 1:
                setFloat(mParamsLocation, 1.0f);
                break;
            case 2:
                setFloat(mParamsLocation, 0.8f);
                break;
            case 3:
                setFloat(mParamsLocation, 0.6f);
                break;
            case 4:
                setFloat(mParamsLocation, 0.4f);
                break;
            case 5:
                setFloat(mParamsLocation, 0.33f);
                break;
            default:
                break;
        }
    }

    public void onBeautyLevelChanged() {
        setBeautyLevel(FilterParams.beautyLevel);
    }
}
