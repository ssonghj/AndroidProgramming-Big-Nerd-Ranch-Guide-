## 8. UI 프래그먼트와 프래그먼트 매니저

### 📌 UI 유연성
  + 사용자나 장치가 요구하는 것에 따라 런타임 시에 액티비티의 뷰를 구성하거나 변경하는 능력
  + 액티비티는 이런 유연성 제공하지 않음 -> 액티비티는 특정화면과 강하게 결합

### 📌 프래그먼트
  + 액티비티의 작업 수행을 대행할 수 있는 컨트롤러 객체 (여기서의 작업은 UI 관리)
  + 액티비티는 자신의 UI를 갖는 대신 프래그먼트를 넣을 컨테이너를 가짐.
  
  ✓ 호스팅 : 액티비티가 자신의 뷰 계층 구조에 프래그먼트와 그 뷰를 포함하는 곳을 제공하는 것
  
### 📌 UI 프래그먼트 생성
  + 레이아웃 파일에 위젯들 정의해 UI구성
  + 클래스 생성하고 정의된 레이아웃을 이 클래스의 뷰로 설정
  + 레이아웃으로부터 인플레이트된 위젯들을 코드에 연결

### 📌 fragment의 서브 클래스 생성
```kotlin
class CrimeFragment : Fragment() { }
```
### 📌 프래그먼트 생명주기 함수 구현
  + 프래그먼트 자체 생명주기가 있음
  + 프래그먼트 뷰는 onCreate에서 하지 않고 onCreateView에서 한다.
  + onCreateView에서 생성한 view로 findViewById를 불러온다.
  + TextWatcher와 같이 사용자와 뷰가 상호 작용할 때 뿐만 아니라 장치 회전등으로 인해 뷰 상태가 복원되면서 데이터가 설정될 떄에는 onStart()에 넣어야한다
```kotlin
class CrimeFragment : Fragment(){
  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
  }
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
      //container는 뷰의 부모, 3번인자 : 인플레이트 된 뷰를 부모에게 즉시 추가할 것인지를 알림
      val view = inflater.inflate(R.layout.fragment_crime, container, false)
      return view
   }
   
   override fun onStart(){
     super.onStart()
     val titleWatcher = object : TextWatcher{
      ~~~~
      }
   }
}
```

### 📌 UI 프래그먼트의 호스팅
  + 액티비티의 레이아웃에 프래그먼트의 뷰를 배치할 곳을 정의
  + 프래그먼트 인스턴스의 생명주기를 관리

### 📌 프래그먼트 컨테이너 뷰 정의
```kotlin
<FrameLayout>
~~
</FrameLayout>
```

### 📌 FragmentManager에 UI 프래그먼트 추가
  + 프래그먼트 리스트와 프래그먼트 트랜잭션의 백 스택(back stack)을 처리
  + 프래그먼트의 뷰를 액티비티의 뷰 계층에 추가하고 프래그먼트의 생명주기를 주도하는 책임을 가짐

### 📌 프래그먼트 트랜잭션
  + 프래그먼트 리스트에 프래그먼트를 추가, 삭제, 첨부, 분리, 변경하는데 사용
  + 여러개의 오퍼레이션을 묶어서 수행 가능
  + 프래그먼트로 런타임 시 화면을 구성 또는 변경하는 방법의 핵심
  ✓ fragmentTransaction 클래스는 플루언트 인터페이스를 사용 -> 플루언트 인터페이스 : 코드를 이해하기 쉽게 해주는 객체지향 기법, 일반적으로 함수의 연쇄 호출 형태로 구현
  ✓ 즉, unit 대신 fragmentTransaction 객체를 반환하기 때문에 이 함수들을 연쇄 호출할 수 있음
```kotlin
class MainActivity: AppCompatActivity(){
  override fun onCreate(~){
  ...
  val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
  
  if(currentFragment == null){
    //프래그먼트 트랜잭션
    val fragment = CrimeFragment()
    supportFragmentManager
      .beginTransaction()
      //컨테이너 뷰 id와 CrimeFragment 인스턴스를 매개변수로 가짐 -> 여기서 컨테이너 뷰 id는 액티비티 뷰의 어느 위치에 프래그먼트 뷰가 나타나야하는지를 fragmentManager에 알려주고, FragmentManager의 리스트에서 프래그먼트를 고유하게 식별하는데 사용
      .add(R.id.fragment_container, fragment)
      .commit()
  }
}
```

### 📌 프래그먼트 보존
  + 액티비티 소멸되었다가 다시 생성될 때를 대비해 프래그먼트 리스트에 보존.
  + 장치 회전이나 안드로이드 운영체제의 메모리 회수로 MainActivity가 소멸되었다가 다시 생성되면 메인의 onCreate가 다시 호출되기때문에 보존해둠

### 📌 프래그먼트 생명 주기
  + 생성 -> 중단 -> 일시중지 -> 실행 재개
  ✓ 액티비티 생명주기와의 차이점!! : 안드로이드 운영체제가 아닌 호스팅 액티비티의 fragmentManager가 호출한다는 점, 안드로이드 운영체제는 액티비티가 사용하는 프래그먼트에 관해서는 
