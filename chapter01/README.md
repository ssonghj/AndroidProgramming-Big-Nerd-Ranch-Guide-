## 1. 처음 만드는 안드로이드 애플리케이션

### 📌 뷰
  + UI 만드는 데 사용되는 구성 요소
  + 장치 화면에 보이는 모든 것이 뷰

### 📌 위젯
  + 사용자가 화면을 보면서 상호 작용하는 뷰
  + 화면에 텍스트나 그래픽 등으로 표현되는 것이 있음
  + 모든 위젯은 View 클래스의 인스턴스이거나 View의 서브 클래스 중 하나의 인스턴스
  
### 📌 문자열 리소스 생성
  + res/values/string.xml
  + 문자열을 직접 지정하지 않고 아래처럼 string 파일에서 가져다가 사용할 경우 지역화(localization)를 쉽게 할 수 있음.
```kotlin
<resources>
  <string name="app_name">GeoQuiz</string>
  <string name="question_text">000</string>
</resources>
```

### 📌 리소스와 리소스 ID
  + 리소스 : 애플리케이션의 일부, 코드가 아닌 이미지 파일이나 오디오 파일 및 XML 파일 등
  + 리소스 ID : 리소스 ID 지정 후 코드에서 사용 가능
```kotlin
<Button
  android:id="@+id/true_button"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"/>
```

### 📌 위젯의 참조 얻기
```kotlin
...
trueButton = findViewById(R.id.true_button)
...
```

### 📌 리스너 설정하기
  + 안드로이드 애플리케이션은 이벤트 기반으로 구동
  + 리스너 : 이벤트에 응답하기 위해 생성하는 객체
  + 안드로이드 프레임워크에는 onClick(View) 메서드만 갖는 인터페이스인 View.OnClickListener가 정의되어 있음.
  -> 이를 단일 추상 메서드(Single Abstract Method, SAM)를 갖는 자바 인터페이스를 SAM 이라고 함.
```kotlin
override fun onCreate(savedInstanceState: Bundle?){
  ...
  trueButton.setOnClickListencer{ view: View ->
  }
```

### 📌 토스트 메시지
  + 토스트 : 팝업 메시지, 사용자에게 무언가를 알려주지만 어떤 입력이나 액션도 요구하지 않는 짤막한 메시지
```kotlin
...
Toast.makeText(this, "hi~", Toast.LENGTH_SHORT).show()
...
```

  + 토스트 위치 변경
```kotlin
...
val toast = Toast.makeText(this, "hi~", Toast.LENGTH_SHORT)
//앱 상단으로 변경
toast.setGravity(Gravity.TOP,Gravity.CENTER,Gravity.CENTER)
toast.show()
...
