package id.ac.akakom.mobile.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.databinding.AdapterPageBinding
import id.ac.akakom.mobile.note.ui.page.PageViewModel
import id.ac.akakom.mobile.note.R
import java.text.SimpleDateFormat

class PageAdapter(private val items: List<Page>, private val viewModel: PageViewModel) :
    RecyclerView.Adapter<PageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterPageBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bundle = bundleOf("id" to items[position].id)

        holder.binding.title.text = if (items[position].title != null) items[position].title else "Untitled Page"
        holder.binding.createdAt.text =
                SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(items[position].created_at.time)

        holder.binding.root.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_pageFragment_to_writeFragment, bundle)
        }

        holder.binding.root.setOnLongClickListener {
            val menus = arrayOf("Delete")

            val builder = AlertDialog.Builder(it.context)

            builder.setTitle(R.string.actions)
                .setItems(menus) { _, which ->
                    when (menus[which]) {
                        "Delete" -> {
                            viewModel.delete(items[position])
                            Toast.makeText(
                                it.context,
                                "${if (items[position].title != null) items[position].title else "Untitled Page"} has been deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                .show()

            true
        }
    }

    class ViewHolder(val binding: AdapterPageBinding) : RecyclerView.ViewHolder(binding.root)
}