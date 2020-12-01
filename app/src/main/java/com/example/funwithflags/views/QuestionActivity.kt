package com.example.funwithflags.views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.funwithflags.R
import com.example.funwithflags.data.QuestionDB
import com.example.funwithflags.helpers.Question
import kotlinx.android.synthetic.main.activity_question.*

enum class QuestionMode {
    CHECK_ANSWER,
    NEXT_QUESTION
}

class QuestionActivity : AppCompatActivity() {

    private val questions = QuestionDB.questionList
    private var currentQuestion = 0
    private var chosenAnswer = ""
    private var correctAnswer = ""
    private var mode = QuestionMode.CHECK_ANSWER
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questions.shuffle()
        generateQuestion()

        submitButton.setOnClickListener {
            if (chosenAnswer.isEmpty()) {
                Toast.makeText(this, "Answer is required", Toast.LENGTH_SHORT).show()
            } else {
                when (mode) {
                    QuestionMode.CHECK_ANSWER -> {
                        if (!checkAnswer()) {
                            (it as Button).text = "Next Question"
                            showWrongAnswer()
                            mode = QuestionMode.NEXT_QUESTION
                        } else {
                            score += 1
                            generateQuestion()
                        }
                    }
                    QuestionMode.NEXT_QUESTION -> {
                        generateQuestion()
                        mode = QuestionMode.CHECK_ANSWER
                    }
                }
            }
        }

    }

    private fun generateQuestion() {
        if (currentQuestion >= questions.size) {
            val resultsIntent = Intent(this, ResultsActivity::class.java)
            resultsIntent.putExtra("score", score)
            startActivity(resultsIntent)
            finish()
            return
        }
        clearAllAnswers()
        submitButton.text = "Guess"
        val newQuestion = questions[currentQuestion]
        correctAnswer = newQuestion.correctAnswer.toString().replace("_", " ")
        val wrongAnswers = QuestionDB.generateWrongAnswers(newQuestion);
        count.text = "${currentQuestion + 1} / ${questions.size}"
        flagImage.setImageResource(newQuestion.image)
        when ((0..3).shuffled().first()) {
            0 -> {
                answerOne.text = prepareAnswer(newQuestion)
                answerTwo.text = prepareAnswer(wrongAnswers[0])
                answerThree.text = prepareAnswer(wrongAnswers[1])
                answerFour.text = prepareAnswer(wrongAnswers[2])
            }
            1 -> {
                answerOne.text = prepareAnswer(wrongAnswers[0])
                answerTwo.text = prepareAnswer(newQuestion)
                answerThree.text = prepareAnswer(wrongAnswers[1])
                answerFour.text = prepareAnswer(wrongAnswers[2])
            }
            2 -> {
                answerOne.text = prepareAnswer(wrongAnswers[0])
                answerTwo.text = prepareAnswer(wrongAnswers[1])
                answerThree.text = prepareAnswer(newQuestion)
                answerFour.text = prepareAnswer(wrongAnswers[2])
            }
            3 -> {
                answerOne.text = prepareAnswer(wrongAnswers[0])
                answerTwo.text = prepareAnswer(wrongAnswers[1])
                answerThree.text = prepareAnswer(wrongAnswers[2])
                answerFour.text = prepareAnswer(newQuestion)
            }
        }
        currentQuestion += 1
    }

    private fun prepareAnswer(q: Question): String {
        return q.correctAnswer.toString().replace("_", " ")
    }

    fun selectAnswer(view: View) {
        clearAllAnswers()
        chosenAnswer = (view as Button).text.toString()
        (view as Button).setBackgroundColor(Color.parseColor("#417eeb"))
        (view as Button).setTextColor(Color.parseColor("white"))
    }

    private fun clearAllAnswers() {
        chosenAnswer = ""
        answerOne.setBackgroundColor(Color.parseColor("white"))
        answerOne.setTextColor(Color.parseColor("#566573"))
        answerTwo.setBackgroundColor(Color.parseColor("white"))
        answerTwo.setTextColor(Color.parseColor("#566573"))
        answerThree.setBackgroundColor(Color.parseColor("white"))
        answerThree.setTextColor(Color.parseColor("#566573"))
        answerFour.setBackgroundColor(Color.parseColor("white"))
        answerFour.setTextColor(Color.parseColor("#566573"))
    }

    private fun checkAnswer(): Boolean {
        return chosenAnswer.replace(" ", "_") == correctAnswer.replace(" ", "_")
    }

    private fun showWrongAnswer() {
        when (chosenAnswer) {
            answerOne.text.toString() -> markAnswerWrong(answerOne)
            answerTwo.text.toString() -> markAnswerWrong(answerTwo)
            answerThree.text.toString() -> markAnswerWrong(answerThree)
            answerFour.text.toString() -> markAnswerWrong(answerFour)
        }
        when (correctAnswer) {
            answerOne.text.toString() -> markAnswerRight(answerOne)
            answerTwo.text.toString() -> markAnswerRight(answerTwo)
            answerThree.text.toString() -> markAnswerRight(answerThree)
            answerFour.text.toString() -> markAnswerRight(answerFour)
        }
    }

    private fun markAnswerWrong(b: Button) {
        b.setBackgroundColor(Color.parseColor("#ec7063"))
        b.setTextColor(Color.parseColor("white"))
    }

    private fun markAnswerRight(b: Button) {
        b.setBackgroundColor(Color.parseColor("#82e0aa"))
        b.setTextColor(Color.parseColor("white"))
    }
}