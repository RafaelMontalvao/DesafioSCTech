const show = ref(false)
const message = ref('')
const color = ref('success')
const timeout = ref(5000)

export function useNotification (){
    const success = (msg, duration = 5000) =>{
        message.value = msg
        color.value = 'success'
        timeout.value = duration
        show.value = true
    }

    const error = (msg, duration = 5000) =>{
        message.value = msg
        color.value = 'error'
        timeout.value = duration
        show.value  = true

    }

    return {
        success,
        error,
        show,
        color,
        timeout,
        message
    }
}