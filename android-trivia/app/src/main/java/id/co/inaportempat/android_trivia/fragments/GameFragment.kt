package id.co.inaportempat.android_trivia.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import id.co.inaportempat.android_trivia.R
import id.co.inaportempat.android_trivia.model.CreateQuestion
import id.co.inaportempat.android_trivia.model.Question
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private lateinit var question: Question


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomizeQuestion()

        submitButton.setOnClickListener { view ->
            val checkedId = questionRadioGroup.checkedRadioButtonId

            if (checkedId != -1) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }

                if (question.answers[answerIndex] == question.answers[0]) {
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                } else {
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }
    }

    private fun randomizeQuestion() {
        val questions = CreateQuestion.questionList()
        questions.shuffle()
        question = questions[0]
        initViews(question)
    }

    private fun initViews(question: Question) {
        Log.d("question", question.text)
        Log.d("answer", question.answers[0])

        questionText.text = question.text
        firstAnswerRadioButton.text = question.answers[0]
        secondAnswerRadioButton.text = question.answers[1]
        thirdAnswerRadioButton.text = question.answers[2]
        fourthAnswerRadioButton.text = question.answers[3]
    }


}
