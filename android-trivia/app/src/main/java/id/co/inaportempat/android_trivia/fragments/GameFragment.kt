package id.co.inaportempat.android_trivia.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        initQuestion()
    }

    private fun initQuestion() {
        CreateQuestion.questionList().shuffle()
        question = CreateQuestion.questionList()[0]
        initViews(question)
    }

    private fun initViews(question: Question) {
        questionText.text = question.text
        firstAnswerRadioButton.text = question.answers[0]
        secondAnswerRadioButton.text = question.answers[1]
        thirdAnswerRadioButton.text = question.answers[2]
        fourthAnswerRadioButton.text = question.answers[3]
    }



}
