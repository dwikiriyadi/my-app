package id.ac.akakom.mobile.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.ac.akakom.mobile.note.R
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.databinding.AdapterPageBinding
import id.ac.akakom.mobile.note.ui.page.PageViewModel
import id.ac.akakom.mobile.note.utility.toast
import java.text.SimpleDateFormat

class PageAdapter(private val items: List<Page>, private val viewModel: PageViewModel) :
    RecyclerView.Adapter<PageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    class ViewHolder private constructor(private val binding: AdapterPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Page, viewModel: PageViewModel) {
            binding.apply {
                this.data = data
                createdAt.text =
                    SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(data.created_at.time)

                root.apply {
                    setOnClickListener {
                        val bundle = bundleOf("id" to data.id)
                        it.findNavController()
                            .navigate(R.id.action_pageFragment_to_writeFragment, bundle)
                    }
                    setOnLongClickListener {
                        val context = it.context

                        val menus = arrayOf("Delete")

                        val builder = AlertDialog.Builder(it.context)

                        builder.setTitle(R.string.actions)
                            .setItems(menus) { _, which ->
                                when (menus[which]) {
                                    "Delete" -> {
                                        viewModel.apply {
                                            delete(data)
                                            getAll(sectionId.value!!)
                                        }

                                        context.toast("${data.title ?: "Untitled Page"} has been deleted")
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

                val binding = AdapterPageBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}