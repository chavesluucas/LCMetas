import icon from '../../assets/img/notification-icon.svg'
import './styles.css'

export function NotificationButton(){
    return(
        <div className="lcmeta-red-btn">
            <img src={icon} alt="Icone de notificar" />
        </div>
    )
}