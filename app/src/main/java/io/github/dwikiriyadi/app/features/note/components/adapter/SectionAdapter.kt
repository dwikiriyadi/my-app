package io.github.dwikiriyadi.app.features.note.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.dwikiriyadi.app.R
import io.github.dwikiriyadi.app.core.objects.entity.Section
import io.github.dwikiriyadi.app.databinding.AdapterSectionBinding
import io.github.dwikiriyadi.app.databinding.DialogEditTextBinding
import io.github.dwikiriyadi.app.core.viewmodels.section.SectionViewModel
import io.github.dwikiriyadi.app.core.common.toast

class SectionAdapter(private val items: List<Section>, private val viewModel: SectionViewModel) :
    RecyclerView.Adapter<SectionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    class ViewHolder private constructor(private val binding: AdapterSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Section, viewModel: SectionViewModel) {
            binding.apply {
                title = data.title
                root.apply {
                    val bundle = bundleOf("io" to data.id, "section_name" to data.title)
                    setOnClickListener {
                        it.findNavController().navigate(R.id.action_sectionFragment_to_pageFragment, bundle)
                    }

                    setOnLongClickListener {
                        val context = it.context

                        val menus = arrayOf("Update", "Delete")

                        val builder = AlertDialog.Builder(it.context)
                        builder.setTitle(R.string.actions)
                            .setItems(menus) { _, which ->
                                when (menus[which]) {
                                    "Update" -> {
                                        val updateDialog = AlertDialog.Builder(it.context)
                                        val view =
                                            DialogEditTextBinding.inflate(LayoutInflater.from(it.context))

                                        updateDialog.setTitle(R.string.update_section)
                                            .setView(view.root)
                                            .setNegativeButton(R.string.cancel) { _, _ ->

                                            }
                                            .setPositiveButton(R.string.add) { _, _ ->
                                                data.title = view.editText.text.toString()
                                                viewModel.update(data)
                                                viewModel.getAll()
                                            }.show()
                                    }
                                    "Delete" -> {
                                        viewModel.delete(data)
                                        viewModel.getAll()
                                        context.toast("${data.title} has been deleted")
                                    }
                                }
                            }
                            .show()

                        true
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                val binding = AdapterSectionBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}