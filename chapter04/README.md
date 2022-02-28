## 4. UI 상태 유지하기

### 📌 ViewModel 추가하기
  + 모델 데이터를 화면에 보여주는 기능 수행.
  + 화면에서 필요한 모든 데이터를 한곳에서 종합하고 데이터를 형식화할 수 있음.
  + ViewModel은 액티비티를 참조하지 않음. -> 메모리 유실이 생길 수 있기 때문임.

#### ✔ ViewModel 인스턴스가 액티비티 인스턴스에 대해 강한 참조를 가질 시 
  1. 액티비티 인스턴스가 메모리에서 제거되지 않아 이 인스턴스가 사용하는 메모리가 유실됨.
  2. ViewModel 인스턴스가 현재 사용되지 않는 과거 액티비티의 참조를 가지게 되어 ViewModel 인스턴스가 과거 액티비티의 뷰를 변경하려할 시 IllegalStateException발생

#### ✔ by lazy 키워드
  + val 속성으로 선언 후 나중에 초기화

### 📌 프로세스 종료 시 데이터 보존
####  ✔ SIS(Saved Instance State, 저장된 인스턴스 상태)
  + 안드로이드 OS가 일시적으로 액티비티 외부에 저장하는 데이터
  + Activity.onSaveInstanceState(Bundle)을 오버라이드해 SIS에 데이터를 추가할 수 있음.
```kotlin
override fun onSaveInstanceState(savedInstanceState: Bundle){
  super.onSaveInstanceState(savedInstanceState)
  savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
}
```

### 📌 SIS와 액티비티 레코드
#### ✔ 액티비티 소멸에도 어떻게 onSaveInstanceState(Bundle)에 저장된 데이터가 존속하는가? 
  + onSaveInstanceState(Bundle) 호출시 데이터가 저장된 Bundle객체는 안드로이드 os에 의해 액티비티의 액티비티 레코드로 저장되기 때문.
  
### 📌 ViewModel vs SIS
####  ✔ ViewModel
  + 액티비티의 동적 데이터 처리시 유용
  + 사용자가 액티비티 끝내면 자동으로 클린업
  + 장치 구성 변경이 생겨 UI에 넣는데 필요한 많은 데이터에 빠르고 쉽게 접근하고자 메모리에 캐싱 시 사용

#### but process 종료되면 ViewModel이 처리할 수 없음. 자신이 가진것이 메모리에서 완전히 제거되기 때문.

####  ✔ 그래서 SIS 
  + 제약 : 직렬화되어 디스크에 저장되기 때문에 크고 복잡한 객체는 지양
  + UI 다시 생성시 위한 소량의 정보에 사용
