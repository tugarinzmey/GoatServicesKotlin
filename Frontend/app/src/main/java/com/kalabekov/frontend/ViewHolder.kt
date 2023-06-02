import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kalabekov.frontend.databinding.ViewHolderBinding

class ViewHolder(binding: ViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
        return super.toString() + " '" + contentView.text + "'"
    }
}