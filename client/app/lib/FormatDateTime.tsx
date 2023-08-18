function formatDateTime(dateTime: string) {
    const options: Intl.DateTimeFormatOptions = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
    };

    const date = new Date(dateTime);
    return date.toLocaleString("sv-SE", options);
}

export default formatDateTime;
