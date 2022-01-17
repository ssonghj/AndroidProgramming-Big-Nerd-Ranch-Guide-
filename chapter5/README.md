## 5. 안드로이드 앱의 디버깅

### 📌 Logcat

### 📌 breakpoint
  + 설정된 해당 라인이 실행되기 전 실행을 일시 중지시켜 그다음부터 어떻게 실행되는지 코드를 살펴볼 수 있게 해줌.
  + 디버깅 모드로 실행해야한다.
  
#### ✔ 실행중인 앱을 다시 시작하지 않고 디버깅해야하는 경우
  + 안드로이드 프로세스에 디버거 연결 버튼 클릭

### 📌 스택 기록 로깅
```kotlin
Log.d(TAG,"~~")
```

### 📌 안드로이드 Lint
  + 안드로이드 코드의 정적 분석기(앱 실행하지 않고 코드 검사해 결함 찾는 프로그램)
  
  ```Analyze -> Inspect Code...```

### 📌 프로젝트 빌드 문제 해결
  + 리소스 파일들의 XML 코드가 제대로 작성되었는지 재확인
  + 프로젝트 청므부터 다시 빌드 ```Build -> Clean Project```
  + 그래들 사용해 현재 설정에 맞게 프로젝트 동기화 ```File -> Sync Project with Gradle Files```
  + 안드로이드 Lint를 실행
  
### 📌 프로파일러
  + 안드로이드 장치의 리소스(cpu, 메모리, 네트워크)를 앱이 어떻게 사용하고 있는지 상세내역을 보여줌.
  + 앱성능 측정하고 조정하는데 유용
```Edit Configurations... -> Profiling -> Enable advanced profiling for older devices -> Apply/ok```
