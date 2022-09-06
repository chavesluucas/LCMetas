import logo from '../../assets/img/logo.svg'
import './styles.css'

export function Header() {
    return (
        <header>
            <div className="lcmeta-logo-container">
                <img src={logo} alt="DSMeta" />
                <h1>LCMeta</h1>
                <p>
                    Desenvolvido por <a href="https://www.linkedin.com/in/lucas-emanuel-chaves-b5a3971b5/" target="_blank">Lucas Emanuel Chaves</a>
                </p>
            </div>
        </header>
    )
}