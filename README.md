# CustomKeyboard

* [自定义键盘一](https://juejin.im/post/5b90ef26f265da0af502fd66)

* [自定义键盘二](https://juejin.im/post/5ba98bfe6fb9a05cf3712f06)



## 效果图

![加载中...](https://user-gold-cdn.xitu.io/2018/9/25/1660e46d51c01ffd?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
![加载中...](https://user-gold-cdn.xitu.io/2018/9/27/1661900eb5379e43?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

## 基本使用


        keyboardViewManager = KeyboardViewManager
                .builder()
                .bindEditText(edit1, edit2, edit3)//需要使用自定义键盘的控件
                .showSystemKeyboard(edit4)//使用系统键盘
                .bindEditTextCallBack(edit1, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {

                    }
                })//自定义键盘回调监听
                .bindEditTextCallBack(edit2, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {

                    }
                })//自定义键盘回调监听
                .build(this)
                .addKeyboardView(rootView);//键盘挂载的跟布局
                










                
                
                
                
