let notifyWrap = document.querySelector('.notify-wrap');
if (!notifyWrap) {
    notifyWrap = document.createElement('div')
    notifyWrap.className = 'notify-wrap'
    document.body.appendChild(notifyWrap)
}

// type=s|e|i|w
export default function notify(msg, type = 'i') {
    const notify = document.createElement('div')
    notify.className = 'notify-item fade-in'
    notify.innerHTML = msg
    notify.classList.add(type)
    notifyWrap.appendChild(notify)
    setTimeout(() => {
        notify.remove()
    }, 1300)
}