# 功能

- ViewModel
- LiveData
- DateBinding
- Localizationg
- Orientation



# Android中Button的Backgroud背景设置默认为蓝紫色，且无法修改的问题

参考https://blog.csdn.net/qq_43417003/article/details/109786563

```
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.HelloWorld" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
        <!-- Customize your theme here. -->
    </style>
</resources>
```

将

```
 <style name="Theme.HelloWorld" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
```

修改为

```
<style name="Theme.HelloWorld" parent="Theme.MaterialComponents.DayNight.DarkActionBar.Bridge">
```

