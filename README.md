# CCompileEngine
在Android平台用gcc和g++编译运行c和c++


## Install
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化
        CCppEngine.install(this);
    }
}
```
## Install Gcc
传入 gcc zip file 的文件路径，您可以吧gcc.zip放到 assets， 或者服务器轴再下载到本地然后安装，以减少apk大小
* 您应该在编译前安装好 Gcc
```java
 CCppEngine.installGcc(this,new File("/storage/emulated/0/Android/data/com.xiaoyv.ccompileengine/files/gcc.zip"),new CCppEngine.OnInstallListener(){
            @Override
            public void onSuccess() {
                Log.e("aaaa", "安装成功");
                Toast.makeText(MainActivity.this, "安装成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
```

## Compile C
```java
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
```
## Compile Cpp
```java
        File cFile = new File("/storage/emulated/0/Android/data/com.xiaoyv.ccompileengine/files/Main.cpp");
        List<File> files = new ArrayList<>();
        files.add(cFile);

        Log.e("==========", "======================================================================");
        CCppEngine.getCppCompiler().compile(files, new CompileCallback() {
            @Override
            public void onSuccess(String outPath) {
                Log.e("==========", "编译完成：" + outPath);


            }

            @Override
            public void onError(String error) {
                Log.e("==========", "编译失败：" + error);

            }
        });
```

## Run
下面是自带的`CExecutor`来运行，你可以引入终端模拟器来更好的执行编译出的二进制文件
```java
                File file = new File(outPath);

                CCppEngine.getCExecutor().exec(file, new ExecuteListener() {
                    @Override
                    public void onExecuteStart(Process process) {
                        Log.e("开始>>>>>>>", " process start");
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
```

## 反馈
QQEmail:1223414335@qq.com

