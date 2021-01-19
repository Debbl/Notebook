### 1. build.gradle 添加

```
android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"
	//添加这行就算引入了
    dataBinding {
        enabled = true
    }

    defaultConfig {
        .......
    }
    buildTypes {
       .........
    }
}
```

### 2. 在xml布局添加根布局

```
<layout>...</layout>
```

### 3. 替换MainActivity代码

```
// setContentView(R.layout.activity_main);
activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
```

### 4. 作用

可以通过activityMainBinding来获取layout\activity_main.xml中的所有组件

### 5. 其他

可以直接在xml中绑定事件

xml的data标签添加MyViewModel

```
<data>
    <variable
    name="MyViewModel"
    type="run.aiwan.datatbinding.MyViewModel" />
</data>
```

获取MyViewModel的值

```
android:text="@{String.valueOf(MyViewModel.number)}"
```

执行MyViewModel的函数

```
android:onClick="@{()->MyViewModel.add()}"
```

MainActivity可以简化为

```
public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // 给activityMainBinding设置数据
        activityMainBinding.setMyViewModel(myViewModel);
        activityMainBinding.setLifecycleOwner(this);
    }
}
```

