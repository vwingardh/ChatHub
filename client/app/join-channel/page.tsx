import JoinChannelLinkForm from "../components/JoinChannelLinkForm";
import SearchChannels from "../components/SearchChannels";

export default function JoinChannelOptions() {
    return (
        <>
            <main>
                <SearchChannels></SearchChannels>
                <JoinChannelLinkForm></JoinChannelLinkForm>
            </main>
        </>
    );
}
