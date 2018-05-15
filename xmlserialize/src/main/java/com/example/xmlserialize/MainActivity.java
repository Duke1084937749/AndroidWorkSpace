package com.example.xmlserialize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //请求一个窗体的常量 FEATURE_NO_TITLE：去掉TitleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public void save(View view) {
        String name = etName.getText().toString().trim();
        String num = etNum.getText().toString().trim();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(num)){
            Toast.makeText(this, "姓名或者学号不正确", Toast.LENGTH_SHORT).show();
        }

        try {
            File file = new File(getFilesDir(), num + "xml");
            FileOutputStream os = new FileOutputStream(file);
            //采用谷歌api 生成xml文件
            //1.获取一个xml文件的生成器 或者别名叫 xml序列号器
            XmlSerializer xmlSerializer = Xml.newSerializer();
            //2.初始化序列化器 作用：设置写到哪个文件 采用什么样的编码
            xmlSerializer.setOutput(os,"UTF-8");
            //3.开始写数据
            xmlSerializer.
            ("UTF-8",true);
            xmlSerializer.startTag(null,"Students");
            //姓名
            xmlSerializer.startTag(null,"name");
            //写xml文件里面的文本
            xmlSerializer.text(name);
            xmlSerializer.endTag(null,"name");
            //学号
            xmlSerializer.startTag(null,"number");
            xmlSerializer.text(num);
            xmlSerializer.endTag(null,"number");
            //性别
            xmlSerializer.startTag(null,"sex");
            //判断RadioGroup里面的ID,是男还是女
            if (rgSex.getCheckedRadioButtonId() == R.id.nan) {
                xmlSerializer.text("男");
            }else {
                xmlSerializer.text("女");
            }
            xmlSerializer.endTag(null,"sex");
            xmlSerializer.endTag(null,"students");
            xmlSerializer.endDocument();
            os.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
