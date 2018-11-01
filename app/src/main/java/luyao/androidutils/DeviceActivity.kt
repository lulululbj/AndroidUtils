package luyao.androidutils

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_device.*
import luyao.lib.DeviceUtils

/**
 * Created by luyao
 * on 2018/10/30 10:23
 */
class DeviceActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        setDeviceInfo()

        RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe { grant ->
                    if (grant) {
                        imei.text = DeviceUtils.getIMEI(this)
                        meid.text = DeviceUtils.getMEID(this)
                        sn.text = DeviceUtils.getSN()
                    }
                }
    }

    private fun setDeviceInfo() {
        brand.text = DeviceUtils.getBrand()
        display.text = DeviceUtils.getDisplay()
        product.text = DeviceUtils.getProduct()
        device.text = DeviceUtils.getDevice()
        board.text = DeviceUtils.getBoard()
        model.text = DeviceUtils.getModel()
        manufacturer.text = DeviceUtils.getManufacturer()
        bootloader.text = DeviceUtils.getBootloader()
        hardware.text = DeviceUtils.getHardware()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            abis.text = DeviceUtils.getSupportedAbis()!!.contentToString()
        }
        radioVersion.text = Build.getRadioVersion()
        api.text = DeviceUtils.getSDK().toString()
        version.text = DeviceUtils.getVersion()
        os.text = DeviceUtils.getKernelVersion()
        macAddress.text = DeviceUtils.getMacAddress()
    }
}