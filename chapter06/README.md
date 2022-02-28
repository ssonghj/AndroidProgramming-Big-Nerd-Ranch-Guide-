## 6. 두 번째 액티비티 만들기

### 📌 새로운 액티비티 생성
  + AndroidManifest.xml에 선언해주어야 함.
```HTML
<application 
  ...
  <activity android:name=".CheatActivity"> // . 은 manifest 요소의 package 속성에 지정된 패키지에 CheatActivity 클래스가 위치함을 안드로이드 os에 알림.
  </activity>
</application>
```

### 📌 액티비티 시작시키기
```kotlin
startActivity(intent)
```
  + ActivityManager라는 안드로이드 운영체제에 전달

### 📌 Intent
  + intent : 컴포넌트가 운영체제와 통신하는 데 사용할 수 있는 객체
  + ActivityManager는 시작시킬 액티비티 클래스가 매니페스트의 activity요소에 선언되어 있는지 확인함.
```kotlin
val intent = Intent(this, CheatActivity::class.java)
startActivity(intent)
```
#### ✔ 명시적 intent
  + Context 객체와 Class 객체를 사용해서 생성하는 Intent
  + 앱 내부에 있는 액티비티를 시작시키기 위해 사용함.
  + 
#### ✔ 암시적 intent
  + 한 애플리케이션의 액티비티에서 다른 애플리케이션의 액티비티를 시작시킬 시 사용.

### 📌 Intent Extra
  + 엑스트라 : 호출하는 액티비티가 인텐트에 포함시킬 수 있는 임의의 데이터로 생성자 인자로 생각 가능.
  + 액티비티 인스턴스는 안드로이드 os에 의해 생성되고 그 생명주기가 관리됨.
  + 키와 값이 한쌍으로 된 구조
```kotlin
Intent.putExtra(name:String, value:Boolean)
```

### 📌 동반 객체 (Companion object)
  + 클래스 인스턴스를 생성하지 않고 동반 객체의 함수를 사용할 수 있음.
  + 자바의 static 과 유사
  + 동반 객체를 포함하는 클래스 이름을 사용해 동반 객체의 함수를 호출할 수 있음.
```kotlin
companion object{
  fun newIntent(packageContext : Context, answerIsTrue: Boolean): Intent{
    return Intent(packageContext, CheatActivity::class.java).apply{
      putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
    }
  }
}
```

### 📌 자식 액티비티에서 결과 돌려받기
  + startActivityForResult
  + 첫 매개 변수는 Intent
  + 두번째 매개 변수는 요청코드로 사용자가 정의한 정수 : 자식 액티비티에 전달되었다가 부모 액티비티가 다시 돌려받으며 부모 액티비티가 여러 타입의 자식 액티비티들을 시작시킬 때 어떤 자식 액티비티가 결과를 돌려주는 것인지 알고자할 때도 사용된다.
```kotlin
 Activity.startActivityForResult(Intent, Int)
```

#### ✔ 결과 데이터 설정하기
  + setResult
  + 결과 코드는 Activity.RESULT.OK 또는 Activity.RESULT_CANCELED 중 하나.
```kotlin
setResult(resultCode:Int, data: Intent)
```
#### ✔ 결과 데이터 처리하기
  + onActivityResult(...)
  + 요청코드와 결과 코드가 기대한 값인지 확인.
```kotlin
onActivityResult(requestCode, resultCode, data)
```
### 📌 액티비티 사이 이동시 안드로이드 os의 동작
  + 안드로이드 os는 앱의 런처(launcher) 액티비티를 실행
  + 론처 액티비티는 매니페스트의  MainActivity 선언부의 intent-filter요소로 정의된다
```kotlin
 <application
  ...>
    <activity android~~>
      <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        
        <category android:name="android.intent.category.LAUNCHER"/>
      </intent-filter>
    </activity>
  </application>
```
