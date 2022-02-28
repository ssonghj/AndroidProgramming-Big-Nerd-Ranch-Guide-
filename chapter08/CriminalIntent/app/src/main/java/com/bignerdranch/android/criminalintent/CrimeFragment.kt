package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    //bundle 객체는 자신의 상태 데이터를 저장하거나 가져온다 -> 액티비티와 프래그먼트 둘다 가짐
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime,container,false)

        //fragment 클래스에는 activity.findviewById가 없으므로 view.findviewById 호출
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply{
            //crime페이지의 데이터 값 가져오기
            text = crime.date.toString()
            isEnabled = false;
        }

        return view
    }

    //onStart()에 TextWatcher 리스너를 넣어야 뷰상태가 복원된 후에 리스너가 실행되어 데이터 정상 설정된다.
    //만약 onCreate나 onCreateView등에 설정하면 뷰 상태 복원 전에 실행되어 복원되지 못한 데이터가 설정되는 문제가 생긴다.
    override fun onStart(){
        super.onStart()

        val titleWatcher = object : TextWatcher{

            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
               //비워두기
            }

            //입력된 텍스트 바뀌면 저장
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
               crime.title = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                //비워두기
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        //체크박스 상태 변경
        //첫 번째 인자는 현재view 뜻함 여기서 사용할 일이 없어 _로 생략, 두번째 인자는 boolean값
        solvedCheckBox.apply{
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }
    }
}