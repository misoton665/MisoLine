package com.misoton.misoline.command

import com.misoton.misoline.data.{User, ApplicationData}

class CreateCommand extends Command {
  val ARG_SIZE = 4

  override def run(args: List[String]): CommandResult = {
    if (args.size < ARG_SIZE + 1) {
      return CommandResult(CommandResult.ERR, "Number of argument error.")
    }

    val id    = args(1)
    val name  = args(2)
    val phone = args(3)
    val email = args(4)

    val newUser = User(id, name, phone, email)

    ApplicationData.addUser(newUser)
    ApplicationData.checkoutUser(newUser)

    val allUser = ApplicationData.getUserData

    println("** User List **")
    for(user <- allUser){
      println(user.name)
    }

    CommandResult(CommandResult.OK, "Create User as ID: " + id + " NAME: " + name + " PHONE: " + phone + " EMAIL: " + email + ".")
  }
}
