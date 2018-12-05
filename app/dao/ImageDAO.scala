package dao

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


class PhotoDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

}
