package com.example.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class ContactActivity : AppCompatActivity() {
    var contacts: MutableList<Contact> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvContacts = findViewById<RecyclerView>(R.id.rvContacts)

        contacts.add(Contact("Jean Pierre","Pernaut", "https://resize-pdm.francedimanche.ladmedia.fr/rcrop/635,500/img/2019-12/bestimage-00390596-002442.jpg?version=v1"))
        contacts.add(Contact("Jeanne","D'Arc", "https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"))
        contacts.add(Contact("Pierre","Menez", "https://images.ladepeche.fr/api/v1/images/view/5ddcfe798fe56f20f9058c96/large/image.jpg?v=1"))
        contacts.add(Contact("Arthur","Rimbaut","https://upload.wikimedia.org/wikipedia/commons/1/1c/Rimbaud.PNG"))
        contacts.add(Contact("Richard","Coeur de Lion", "https://i2.wp.com/www.histoire-normandie.fr/wp-content/uploads/2016/08/richard_coeur_lion_blondel.jpg?resize=364%2C650"))
        contacts.add(Contact("Zinedine","Zidane", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Zinedine_Zidane_by_Tasnim_01.jpg/280px-Zinedine_Zidane_by_Tasnim_01.jpg"))
        contacts.add(Contact("Yannick","Noah", "https://static1.purepeople.com/articles/0/31/69/40/@/4489916-exclusif-yannick-noah-enregistrement-624x600-2.jpg"))

        val adapter = ContactAdapter(contacts, this)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}