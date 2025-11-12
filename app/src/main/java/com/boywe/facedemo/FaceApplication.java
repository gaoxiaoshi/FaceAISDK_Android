package com.boywe.facedemo;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.camera2.Camera2Config;
import com.faceAI.demo.FaceSDKConfig;
import com.tencent.bugly.crashreport.CrashReport;


public class FaceApplication extends Application implements CameraXConfig.Provider {

    /**
     * CameraX 会枚举和查询设备上可用摄像头的特性。由于 CameraX 需要与硬件组件通信，因此对每个摄像头执行此过程可能
     * 需要较长时间，尤其是在低端设备上。如果您的应用仅使用设备上的特定摄像头（例如默认前置摄像头）您可以将 CameraX
     * 设置为忽略其他摄像头，从而缩短应用所用摄像头的启动延迟时间。
     *
     * 更多：https://developer.android.com/media/camera/camerax/configuration?hl=zh-cn
     * @return CameraXConfig
     */
    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
//                .setAvailableCamerasLimiter(CameraSelector.DEFAULT_FRONT_CAMERA)
                .setMinimumLoggingLevel(Log.ERROR)
                .build();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // 人脸图保存路径等初始化配置迁移到 Application
        try {
            FaceSDKConfig.init(this);
        } catch (Throwable t) {
            Log.e("FaceApplication", "FaceSDKConfig.init error", t);
        }

        // 收集 Crash、ANR 运行日志（仅非调试包）
        try {
            if (!BuildConfig.DEBUG) {
                CrashReport.initCrashReport(this, "36fade54d8", true);
            }
        } catch (Throwable t) {
            Log.e("FaceApplication", "CrashReport init error", t);
        }
    }



}