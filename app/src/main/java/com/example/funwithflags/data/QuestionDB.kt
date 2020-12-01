package com.example.funwithflags.data

import android.util.Log
import com.example.funwithflags.R
import com.example.funwithflags.helpers.Question

object QuestionDB {

	val questionList = arrayListOf(
			Question(
					1,
					R.drawable.ic_flag_of_argentina,
					Countries.ARGENTINA
			),
			Question(
					2,
					R.drawable.ic_flag_of_australia,
					Countries.AUSTRALIA
			),
			Question(
					3,
					R.drawable.ic_flag_of_belgium,
					Countries.BELGIUM
			),
			Question(
					4,
					R.drawable.ic_flag_of_brazil,
					Countries.BRAZIL
			),
			Question(
					5,
					R.drawable.ic_flag_of_denmark,
					Countries.DENMARK
			),
			Question(
					6,
					R.drawable.ic_flag_of_fiji,
					Countries.FIJI
			),
			Question(
					7,
					R.drawable.ic_flag_of_germany,
					Countries.GERMANY
			),
			Question(
					8,
					R.drawable.ic_flag_of_india,
					Countries.INDIA
			),
			Question(
					9,
					R.drawable.ic_flag_of_kuwait,
					Countries.KUWAIT
			),
			Question(
					10,
					R.drawable.ic_flag_of_new_zealand,
					Countries.NEW_ZEALAND
			),
	)

	fun generateWrongAnswers(q: Question): ArrayList<Question> {
		val results = ArrayList<Question>()
		for (i in 0..2) {
			var mode = 0
			while (mode == 0) {
				val temp = questionList.random()
				if (temp.id != q.id && !results.contains(temp)) {
					results.add(temp)
					mode = 1
				}
			}
		}
		return results
	}

}