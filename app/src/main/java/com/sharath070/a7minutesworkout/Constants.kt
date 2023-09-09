package com.sharath070.a7minutesworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val pushUps = ExerciseModel(
            2,
            "Push Ups",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUps)

        val tricepsDip = ExerciseModel(
            3,
            "Triceps Dip",
            R.drawable.ic_triceips_dip,
            false,
            false
        )
        exerciseList.add(tricepsDip)

        val crossBodyToe = ExerciseModel(
            4,
            "Cross Body Toe Touch",
            R.drawable.ic_cross_body_toe,
            false,
            false
        )
        exerciseList.add(crossBodyToe)

        val plank = ExerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val crunches = ExerciseModel(
            6,
            "Abdominal Crunches",
            R.drawable.ic_abdominal_crunches,
            false,
            false
        )
        exerciseList.add(crunches)

        val wallSit = ExerciseModel(
            7,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        val highKnees = ExerciseModel(
            8,
            "High Knees",
            R.drawable.ic_high_knee,
            false,
            false
        )
        exerciseList.add(highKnees)

        val squats = ExerciseModel(
            9,
            "Squats",
            R.drawable.ic_squats,
            false,
            false
        )
        exerciseList.add(squats)

        val lunges = ExerciseModel(
            10,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunges)

        val sideLunge = ExerciseModel(
            11,
            "Side Lunges",
            R.drawable.ic_side_lunges,
            false,
            false
        )
        exerciseList.add(sideLunge)

        val stepUp = ExerciseModel(
            12,
            "Step Up On Chair",
            R.drawable.ic_step_up_on_chair,
            false,
            false
        )
        exerciseList.add(stepUp)


        return exerciseList
    }
}