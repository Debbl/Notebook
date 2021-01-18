屏幕旋转

不同方向的屏幕对应不同的布局

### 1.屏幕方向锁定

src\main\AndroidManifest.xml 加上

```
android:screenOrientation="portrait"
```

## 2.其他

屏幕方向旋转将导致activity被销毁（数据没保存将会丢失）可以使用    onSaveInstanceState 方法保存数据

