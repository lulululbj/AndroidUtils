package luyao.androidutils

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_device.*
import luyao.lib.DeviceUtils

/**
 * Created by luyao
 * on 2018/10/30 10:23
 */
class DeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        setDeviceInfo()
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
    }
}