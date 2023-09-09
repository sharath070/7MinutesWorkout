package com.sharath070.a7minutesworkout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sharath070.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var _binding: ActivityExerciseBinding? = null
    private val binding get() = _binding!!

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


    private lateinit var exerciseList: ArrayList<ExerciseModel>
    private var currentExercisePosition = -1

    private lateinit var tts: TextToSpeech
    private lateinit var player: MediaPlayer

    private lateinit var exerciseAdapter: ExerciseStatusAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        exerciseList = Constants.defaultExerciseList()

        tts = TextToSpeech(this, this)

        setUpRestView()
        setUpExerciseRV()
    }

    private fun setUpExerciseRV(){
        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList)
        binding.rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun setUpRestView() {

        try {
            val soundUri = Uri.parse(
                "android.resource://com.sharath070.a7minutesworkout/"
                + R.raw.press_start
            )
            player = MediaPlayer.create(applicationContext, soundUri)
            player.isLooping = false
            player.start()
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        binding.flRestView.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.tvExercise.visibility = View.INVISIBLE
        binding.flExerciseView.visibility = View.INVISIBLE
        binding.ivImage.visibility = View.INVISIBLE
        binding.upComingLabel.visibility = View.VISIBLE
        binding.upComingExerciseName.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        binding.upComingExerciseName.text = exerciseList[currentExercisePosition + 1].getName()

        // speakOut("Rest Time.")

        setRestProgressBar()
    }

    private fun setUpExerciseView() {
        binding.flRestView.visibility = View.INVISIBLE
        binding.title.visibility = View.INVISIBLE
        binding.tvExercise.visibility = View.VISIBLE
        binding.flExerciseView.visibility = View.VISIBLE
        binding.ivImage.visibility = View.VISIBLE
        binding.upComingLabel.visibility = View.INVISIBLE
        binding.upComingExerciseName.visibility = View.INVISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList[currentExercisePosition].getName())

        binding.ivImage.setImageResource(exerciseList[currentExercisePosition].getImg())
        binding.tvExercise.text = exerciseList[currentExercisePosition].getName()

        setExerciseProgressBar()
    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.Timer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList[currentExercisePosition].setIsSelected(true)
                exerciseAdapter.notifyDataSetChanged()

                setUpExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30 - exerciseProgress
                binding.TimerExercise.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                exerciseList[currentExercisePosition].setIsSelected(false)
                exerciseList[currentExercisePosition].setIsCompleted(true)
                exerciseAdapter.notifyDataSetChanged()

                if (currentExercisePosition < (exerciseList.size - 1)) {
                    setUpRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations!, You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts.stop()
            tts.shutdown()
        }

        if (player != null){
            player.stop()
        }

        _binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)

            if (
                result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("TTS", "The language specified is no supported.")
            }
        } else {
            Log.e("TTS", "Initialization Failed.")
        }
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}