package id.co.inaportempat.android_trivia.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import id.co.inaportempat.android_trivia.R
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_won.*

/**
 * A simple [Fragment] subclass.
 */
class GameOverFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonTryAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
    }


}
