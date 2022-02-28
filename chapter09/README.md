## 9. RecyclerView로 리스트 보여주기

### 📌 companion object 의 newInstance()
  + 자바의 static 메서드와 유사
  + 특정 클래스의 인스턴스를 생성하지 않고 호출할 수 있음

### 📌 ViewModel가 프래그먼트와 결합시의 생명주기
  + 생성됨, 소멸되어 존재하지 않음의 두가지 상태를 가지고
  + 액티비티 대신 프래그먼트와 생명주기가 결합됌.

  🎈 단, 프래그먼트 트랜잭션을 백스택에 추가할 때, 액티비티가 현재 프래그먼트를 다른것으로 교체 시 트랜잭션이 백스택에 추가된다면 해당 프래그먼트 인스턴스와 이것의 ViewModel은 소멸하지 않음. 
### 📌 RecyclerView 선언
  + RecyclerView를 생성한 이후에는 layoutManager를 바로 설정해주어야한다.
  + 항목들을 화면에 위치시키는 일을 layoutManager에 위임하기 때문.
  + GridLayoutManager를 사용할 수도 있음.
```kotlin
crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
crimeRecyclerView.layoutManager = LinearLayoutManager(context)
```

### 📌 항목 뷰 레이아웃 생성
  + RecyclerView는 ViewGroup의 서브 클래스이고, 항목뷰라고하는 자식 View 객체들의 리스트를 보여줌.
  + 각 항목 View는 RecyclerView의 행으로 나타나고 데이터 저장소에서 가져온 하나의 객체를 나타냄.
  
### 📌 ViewHolder 구현
  + RecyclerView는 항목 View가 ViewHolder 인스턴스에 포함되어 있다고 간주함.
  + ViewHolder는 항목 View의 참조를 가짐.
```kotlin
private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view){
  val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
  ...
}
```

### 📌 어댑터 구현
  + RecyclerView는 자신이 ViewHolder를 생성하지 않고 어댑터에 요청함.
  + 어댑터가 처리하는 일 : 필요한 ViewHolder 인스턴스들을 생성, 모델 계층의 데이터를 ViewHolder들과 바인딩
  + 3개의 함수를 오버라이드 한다. (onCreateViewHolder, getItemCount, onBindViewHolder)
```kotlin
private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>{

  // 같은 recyclerview에 다른 타입의 뷰를 보여줄 때만 필요
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CrimeHolder{
    val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
    return CrimeHolder(view)
  }
  //데이터 개수 확인시 사용
  override fun getItemCount() = crimes.size
  
  //인자로 전달된 위치의 속성을 crimeHolder 인스턴스가 참조하는 속성에 지정.
  override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
    var crime - crimes[position]
    holder.apply{
        titleTextView.text = crime.title
    }
  }
}
```

### 📌 RecyclerView의 어댑터 설정
  + CrimeAdapter를 생성해 RecyclerView에 설정

```kotlin
 private fun updateUI(){
    val crimes = crimeListViewModel.crimes
    adapter = CrimeAdapter(crimes)
    crimeRecyclerView.adapter = adapter
 }
```
#### 🎈 후원 필드 (backing field) : 코틀린은 속성의 값을 보존하는 필드를 내부적으로 유지함. 필드 값을 반환하는 게터와 값을 변경하는 세터가 자동 생성되고 호출된다.

### 📌 뷰의 재활용
  + RecyclerView는 항목 view가 화면을 벗어날 때 해당 항목 View를 버리지 않고 재활용함.
  + 충분한 수의 ViewHolder가 생성되면 RecyclerView는 onCreateViewHolder(...)의 호출을 중단하고 기존의 ViewHolder를 재활용해 onBindViewHolder에 전달하여 시간과 메모리를 절약함.

### 📌 리스트 항목 선택 응답
  + OnclickListener를 통해 터치 이벤트 설정
```kotlin
private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
    private lateinit var crime: Crime
    
    ...
    
    init{
      itemView.setOnClickListener(this)
    }
    
    fun bind(){
      ...
    }
    
    override fun onClick(v: View){
      //토스트 메시지
    }
}
```

### 📌 RecyclerView vs ListView, GridView
  + 위 세가지 중 RecyclerView를 사용하는 이유 : ListView, GridView는 작동방식을 변경하기 어렵고 애니메이션 기능의 구현이 복잡함.
