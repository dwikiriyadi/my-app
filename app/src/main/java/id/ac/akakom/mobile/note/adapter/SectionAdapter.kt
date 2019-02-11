package id.ac.akakom.mobile.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.akakom.mobile.note.data.model.Section
import id.ac.akakom.mobile.note.databinding.AdapterSectionBinding
import id.ac.akakom.mobile.note.ui.section.SectionViewModel
import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.databinding.DialogEditTextBinding

class SectionAdapter(private val items: List<Section>, private val viewModel: SectionViewModel):
RecyclerView.Adapter<SectionAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterSectionBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bundle = bundleOf("id" to items[position].id, "section_name" to items[position].title)

        holder.binding.sectionName.text = items[position].title

        holder.binding.root.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_sectionFragment_to_pageFragment, bundle)
        }

        holder.binding.root.setOnLongClickListener {
            val menus = arrayOf("Update", "Delete")

            val builder = AlertDialog.Builder(it.context)

            builder.setTitle(R.string.actions)
                .setItems(menus) { dialog, which ->
                    when(menus[which]) {
                        "Update" -> {
                            val updateDialog = AlertDialog.Builder(it.context)
                            val view = DialogEditTextBinding.inflate(LayoutInflater.from(it.context))

                            updateDialog.setTitle(R.string.update_section)
                                .setView(view.root)
                                .setNegativeButton(R.string.cancel) { _, _->

                                }
                                .setPositiveButton(R.string.add) { _, _->
                                    items[position].title = view.editText.text.toString()
                                    viewModel.update(items[position])
                                }.show()
                        }
                        "Delete" -> {
                            viewModel.delete(items[position])
                            Toast.makeText(it.context, "${items[position].title} has been deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .show()
            
            true
        }
    }

    class ViewHolder(val binding: AdapterSectionBinding): RecyclerView.ViewHolder(binding.root)
}