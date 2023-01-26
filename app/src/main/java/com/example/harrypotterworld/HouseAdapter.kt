package com.example.harrypotterworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterworld.databinding.ItemHouseBinding
import com.example.harrypotterworld.model.House
import com.example.harrypotterworld.util.FounderImageProvider
import com.squareup.picasso.Picasso

class HouseAdapter(private var houses: List<House>) :
    RecyclerView.Adapter<HouseAdapter.HouseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = ItemHouseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HouseViewHolder(binding)
    }

    override fun getItemCount(): Int = houses.size

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.bind(houses[position])
    }

    /**
     * To avoid using notifyDataSetChanged() and having a better way to be aware of the changes
     * on my list, normally I would be using DiffUtil.ItemCallback. But because of the short time
     * I've done it like this. To more info about DiffUtil.ItemCallback, you can check the next link
     * https://www.kodeco.com/21954410-speed-up-your-android-recyclerview-using-diffutil
     */
    fun submitList(houses: List<House>) {
        this.houses = houses
        notifyDataSetChanged()
    }

    class HouseViewHolder(private val binding: ItemHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(house: House) {
            binding.textFounderName.text = house.founder
            binding.textHouseName.text = house.name
            binding.textAnimal.text = house.animal
            binding.textColor.text = house.color
            Picasso
                .get()
                .load(FounderImageProvider.getFounderImage(house.name))
                .error(R.drawable.no_image)
                .into(binding.imageView)
        }
    }
}