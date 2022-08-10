package com.android.recipe.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.android.recipe.R
import com.android.recipe.databinding.StepItemBinding
import com.android.recipe.presentation.Step

class StepAdapter(
    private val steps: List<Step>
) : BaseAdapter() {

    override fun getCount() = steps.size

    override fun getItem(p0: Int) = steps[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val stepView = StepItemBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
        val step = getItem(p0)
        with(stepView){
            tvNumOfStep.text = step.number.toString()
            tvIngredients.text = step.ingredients
            tvEquipments.text = step.equipments
            tvStepDescription.text = step.description
            if(p0 % 2 == 0){
                container.setBackgroundResource(R.drawable.rounded_peach)
            }
            if(p0 % 3 == 0){
                container.setBackgroundResource(R.drawable.rounded_blue)
            }
        }
        return stepView.root
    }

}