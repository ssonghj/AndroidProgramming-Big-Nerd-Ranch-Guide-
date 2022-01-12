package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //lateinit : 속성을 사용하기 전에 책임지고 초기화하겠다는 것을 컴파일러에게 알려줌.
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    //Question 객체 저장한 리스트
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener{ view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener{ view: View->
            checkAnswer(false)
        }
        //질문 가져와서 화면에 반영
        updateQuestion()

        //질문 총 개수보다 많을 경우 총 개수의 나머지를 활용하여 처음으로 돌아가기, 버튼 클릭시 질문 넘어감
        nextButton.setOnClickListener{
            currentIndex = (currentIndex +1) % questionBank.size
            updateQuestion()
        }

        //챌린지2 - previous 버튼 추가
        previousButton.setOnClickListener{
            //0이 되면 현재 인덱스를 질문 총 개수로 바꾸기
            if(currentIndex == 0) currentIndex=questionBank.size
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

        //챌린지1 - TextView에 리스너 추가하기
        questionTextView.setOnClickListener{
            currentIndex = (currentIndex +1) % questionBank.size
            updateQuestion()
        }


    }
    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }
}