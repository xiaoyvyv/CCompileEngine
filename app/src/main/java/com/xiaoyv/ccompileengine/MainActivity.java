package com.xiaoyv.ccompileengine;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xiaoyv.ccompile.CCppEngine;
import com.xiaoyv.ccompile.compiler.listener.CompileCallback;
import com.xiaoyv.ccompile.compiler.listener.ExecuteListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        File cFile = new File("/storage/emulated/0/Android/data/com.xiaoyv.ccompileengine/files/Main.c");
        List<File> files = new ArrayList<>();
        files.add(cFile);

        Log.e("==========", "======================================================================");
        CCppEngine.getCCompiler().compile(files, new CompileCallback() {
            @Override
            public void onSuccess(String outPath) {
                Log.e("==========", "编译完成：" + outPath);


            }

            @Override
            public void onError(String error) {
                Log.e("==========", "编译失败：" + error);

            }
        });

       /* CCppEngine.installGcc(this,new File("/storage/emulated/0/Android/data/com.xiaoyv.ccompileengine/files/gcc.zip"),new CCppEngine.OnInstallListener(){
            @Override
            public void onSuccess() {
                Log.e("aaaa", "安装成功");
                Toast.makeText(MainActivity.this, "安装成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("RUN").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                File file = new File("/data/user/0/com.xiaoyv.ccompileengine/files/c_compiler/temp");

                CCppEngine.getCExecutor().exec(file, new ExecuteListener() {
                    @Override
                    public void onExecuteStart(Process process) {
                        Log.e("开始>>>>>>>", " process");
                    }

                    @Override
                    public void printStderr(Throwable error) {
                        Log.e("错误>>>>>>>", error.toString());
                        error.printStackTrace();
                    }

                    @Override
                    public void printStdout(CharSequence out) {
                        Log.e("输出>>>>>>>", out.toString());
                    }

                    @Override
                    public void onExecuteFinish(int waitFor, int exitValue) {
                        Log.e("完成>>>>>>>", "====================" + waitFor + "================" + exitValue);
                    }
                });

                return false;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        menu.add("INPUT").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
}
