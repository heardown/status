package me.winds.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lifesense.wificonfig.LSWifiConfigManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import me.winds.wrapper.status.DefaultStatus;
import me.winds.wrapper.status.StatusProvider;

public class StatusActivity extends WrapperStatusActivity {

    @Override
    protected int getViewLayout() {
        return R.layout.activity_status;
    }

    @Override
    protected void initView(Bundle savedInstanceState, Intent intent) {

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_loading:
                getStatusManagerDelegate().showLoadingStatus();
                timer();
                break;
            case R.id.btn_empty:
                getStatusManagerDelegate().showEmptyStatus();
                break;
            case R.id.btn_load_error:
                getStatusManagerDelegate().showLoadErrorStaus();
                break;
            case R.id.btn_network_error:
                getStatusManagerDelegate().showNetErrorStatus();
                break;
            case R.id.btn_no_login:
                getStatusManagerDelegate().showStatus(DefaultStatus.STATUS_NOT_LOGIN);
                break;
            case R.id.btn_custome:
//                String version = LSWifiConfigManager.shared().getClass().getPackage().getImplementationVersion();
//                Package pack = Package.getPackage("com.lifesense.com");
                Package pack = LSWifiConfigManager.class.getPackage();
                Log.i(" -- ", pack.getImplementationVersion());
                Log.i(" -- ",  pack.getSpecificationVersion());
                Package[] packages = pack.getPackages();

                for(int i = 0; i < packages.length; i++) {
                    Log.i(" -- ", packages[i] + " ");
                }

                break;

        }
    }


    private void loadManifest() {
        String path = System.getProperty("wificonfig.jar");// 这个即可获取当前项目所在磁盘路径，那么程序里面就可以根据这个path拼接了
//        LSWifiConfigManager.class.
        System.out.println(path);
        //meta-inf

        // properties的读写问题，网上大多写的不清不楚，比如这个properties里面本来就有个属性key=A，不能随意修改删除！但是我还要给这个properties添加其它属性，修改其它属性、删除其它属性，怎么搞？
        // 这个一不小心就覆盖了，或者之前的properties就没了，正确代码如下：
        Properties props = new Properties();
        // 这里可以利用上面path找到jar包同目录的properties文件，如果properties文件在jar里面，那么是无法修改的，因为IO流写不到jar包里面去
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path + "/MANIFEST.MF");

        props.load(fis);
        props.put("key1", "B"); //增加
        props.put("key2", "C"); //增加
        props.setProperty("key1", "1111"); //修改
        props.setProperty("key2", "king"); //修改
        props.remove("key2"); //删除
        FileOutputStream fos = new FileOutputStream(path + "/my.properties");
        props.store(fos, "注释");
        fis.close();
        fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void timer() {
        getContentView().postDelayed(new Runnable() {
            @Override
            public void run() {
                getStatusManagerDelegate().removeStatus();
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        StatusManagerDelegate delegate = getStatusManagerDelegate();
        StatusProvider provider = delegate.getStatusManager().getCurrentStatusProvider();
        if (provider != null && !provider.isHide()) {
            delegate.removeStatus();
            return;
        }
        super.onBackPressed();
    }
}
