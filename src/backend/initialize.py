from pathlib import Path

def check_songs():
    current_dir = Path(__file__).resolve().parent
    root_dir = current_dir.parents
    songs_dir = root_dir / "songs"

    if not songs_dir.exists():
        songs_dir.mkdir(parents=True, exist_ok=True)

    print("debug2")
    print(songs_dir)

    return songs_dir

songs_path = check_songs()