package com.yiqiding.ktvbox.infomanage;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;

public class InfoXmlIO {
	private XmlPullParserFactory pullFactory;
	private XmlSerializer serializer;
	private XmlPullParser parser;

	public InfoXmlIO() {
		try {
			pullFactory = XmlPullParserFactory.newInstance();
			serializer = pullFactory.newSerializer();
			parser = pullFactory.newPullParser();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}

	public String writeInfoXml(int functionNum, Bundle info) {
		switch (functionNum) {
		case InfoFunctionInterface.functionnum_system_apk:
			return this.writeInfoXml_For_SystemApkFunction(info);
		case InfoFunctionInterface.functionnum_music_list:
			return this.writeInfoXml_For_MusicListFunction(info);
		case InfoFunctionInterface.functionnum_user_info:
			return null;

		default:
			break;
		}

		return null;
	}

	public Bundle parseInfoXml(int functionNum, String contentData) {
		switch (functionNum) {
		case InfoFunctionInterface.functionnum_system_apk:
			return this.parseInfoXml_For_SystemApkFunction(contentData);
		case InfoFunctionInterface.functionnum_system_room:
			return this.parseInfoXml_For_SystemRoomFunction(contentData);
		case InfoFunctionInterface.functionnum_music_list:
			return this.parseInfoXml_For_MusicListFunction(contentData);
		case InfoFunctionInterface.functionnum_music_filter:
			return this.parseInfoXml_For_MusicFilterFunction(contentData);
		case InfoFunctionInterface.functionnum_album_filter:
			return this.parseInfoXml_For_AlbumFilterFunction(contentData);
		case InfoFunctionInterface.functionnum_activity_filter:
			return this.parseInfoXml_for_ActivityFilterFunction(contentData);
		case InfoFunctionInterface.functionnum_music_video:
			return this.parseInfoXml_For_MusicVideoFunction(contentData);
		case InfoFunctionInterface.functionnum_music:
			return this.parseInfoXml_For_MusicFunction(contentData);
		case InfoFunctionInterface.functionnum_album:
			return this.parseInfoXml_For_AlbumFunction(contentData);
		case InfoFunctionInterface.functionnum_singer:
			return this.parseInfoXml_For_SingerFunction(contentData);
		case InfoFunctionInterface.functionnum_singer_music:
			return this.parseInfoXml_For_SingerMusicFunction(contentData);
		case InfoFunctionInterface.functionnum_singer_album:
			return this.parseInfoXml_For_SingerAlbumFunction(contentData);
		case InfoFunctionInterface.functionnum_search_suggest:
			return this.parseInfoXml_For_SearchSuggestFunction(contentData);
		case InfoFunctionInterface.functionnum_search_music:
			return this.parseInfoXml_For_SearchMusicFunction(contentData);
		case InfoFunctionInterface.functionnum_search_album:
			return this.parseInfoXml_For_SearchAlbumFunction(contentData);
		case InfoFunctionInterface.functionnum_search_singer:
			return this.parseInfoXml_For_SearchSingerFunction(contentData);
		case InfoFunctionInterface.functionnum_user_info:
			return this.parseInfoXml_For_UserInfoFunction(contentData);

		default:
			break;
		}

		return null;
	}
	
	public Boolean checkIsErrorInfoXml(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			
			int i = 0;

			while (eventType != XmlPullParser.END_DOCUMENT && i<2) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					i++;
					if (parser.getName().equals(InfoFunctionInterface.error)) {
						return true;
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
	
	private Bundle parseInfoXml_For_SystemApkFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.path)) {
						result.putString(InfoFunctionInterface.path,
								parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_SystemRoomFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.room)) {
						if (parser.nextText().equals("1")) {
							result.putBoolean(InfoFunctionInterface.room, true);
						}
						if (parser.nextText().equals("0")) {
							result.putBoolean(InfoFunctionInterface.room, false);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_MusicListFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<AlbumInfoStructure> albums = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			AlbumInfoStructure album = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int returntype = 0;
			int level = 0;
			Boolean isMusicsLevel = true;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(
							InfoFunctionInterface.returntype)) {
						returntype = Integer.parseInt(parser.nextText());
						result.putInt(InfoFunctionInterface.returntype,
								returntype);
					}
					if (returntype == InfoFunctionInterface.enum_returntype_album) {
						if (parser.getName().equals(
								InfoFunctionInterface.albums)) {
							albums = new ArrayList<AlbumInfoStructure>();
							level = 1;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.album)) {
							album = new AlbumInfoStructure();
							level = 2;
						}
						if (level == 2) {
							if (parser.getName().equals(
									InfoFunctionInterface.albumid)) {
								album.setAlbumId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								album.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.release)) {
								album.setRelease(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.detail)) {
								album.setDetail(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								album.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.musics)) {
								musics = (ArrayList<MusicInfoStructure>) album
										.getMusics();
								level = 3;
								isMusicsLevel = true;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = album.getSingers();
								level = 3;
								isMusicsLevel = false;
							}
						}
						if (isMusicsLevel) {
							if (level == 3) {
								if (parser.getName().equals(
										InfoFunctionInterface.music)) {
									music = new MusicInfoStructure();
									level = 4;
								}
							}

							if (level == 4) {
								if (parser.getName().equals(
										InfoFunctionInterface.musicid)) {
									music.setMusicId(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.name)) {
									music.setName(parser.nextText());
								}
								if (parser.getName().equals(
										InfoFunctionInterface.singers)) {
									singers = music.getSingers();
									level = 5;
								}
								if (parser.getName().equals(
										InfoFunctionInterface.country)) {
									music.setCountry(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.language)) {
									music.setLanguage(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.type)) {
									music.setType(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.length)) {
									music.setLength(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.image)) {
									music.setImage(parser.nextText());
								}
								if (parser.getName().equals(
										InfoFunctionInterface.stars)) {
									music.setStars(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.count)) {
									music.setCount(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.albuminfo)) {
									music.setAlbumInfo(parser.nextText());
								}
							}
							if (level == 5) {
								if (parser.getName().equals(
										InfoFunctionInterface.singer)) {
									singer = new SingerInfoStructure();
									level = 6;
								}
							}
							if (level == 6) {
								if (parser.getName().equals(
										InfoFunctionInterface.singerid)) {
									singer.setSingerId(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.name)) {
									singer.setName(parser.nextText());
								}
								if (parser.getName().equals(
										InfoFunctionInterface.type)) {
									singer.setType(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.nation)) {
									singer.setNation(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.image)) {
									singer.setImage(parser.nextText());
								}
							}
						}
						if (!isMusicsLevel) {
							if (level == 3) {
								if (parser.getName().equals(
										InfoFunctionInterface.singer)) {
									singer = new SingerInfoStructure();
									level = 4;
								}
							}
							if (level == 4) {
								if (parser.getName().equals(
										InfoFunctionInterface.singerid)) {
									singer.setSingerId(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.name)) {
									singer.setName(parser.nextText());
								}
								if (parser.getName().equals(
										InfoFunctionInterface.type)) {
									singer.setType(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.nation)) {
									singer.setNation(Integer.parseInt(parser
											.nextText()));
								}
								if (parser.getName().equals(
										InfoFunctionInterface.image)) {
									singer.setImage(parser.nextText());
								}
							}
						}
					}

					if (returntype == InfoFunctionInterface.enum_returntype_music) {
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							musics = new ArrayList<MusicInfoStructure>();
							level = 1;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							music = new MusicInfoStructure();
							level = 2;
						}
						if (level == 2) {
							if (parser.getName().equals(
									InfoFunctionInterface.musicid)) {
								music.setMusicId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								music.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = music.getSingers();
								level = 3;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.country)) {
								music.setCountry(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.language)) {
								music.setLanguage(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								music.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.length)) {
								music.setLength(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								music.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.stars)) {
								music.setStars(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.count)) {
								music.setCount(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.albuminfo)) {
								music.setAlbumInfo(parser.nextText());
							}
						}
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 4;
							}
						}
						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}

					break;
				case XmlPullParser.END_TAG:
					if (returntype == InfoFunctionInterface.enum_returntype_album) {
						if (!isMusicsLevel) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singers.add(singer);
								level = 3;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								level = 2;
							}
						}
						if (isMusicsLevel) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singers.add(singer);
								level = 5;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								level = 4;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.music)) {
								musics.add(music);
								level = 3;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.musics)) {
								level = 2;
							}
						}

						if (parser.getName()
								.equals(InfoFunctionInterface.album)) {
							albums.add(album);
							level = 1;
						}

						if (parser.getName().equals(
								InfoFunctionInterface.albums)) {
							result.putParcelableArrayList(
									InfoFunctionInterface.albums, albums);
							level = 0;
						}

					}
					if (returntype == InfoFunctionInterface.enum_returntype_music) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 2;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							musics.add(music);
							level = 1;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							result.putParcelableArrayList(
									InfoFunctionInterface.musics, musics);
							level = 0;
						}
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_MusicFilterFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						musics = new ArrayList<MusicInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						music = new MusicInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.musicid)) {
							music.setMusicId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							music.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = music.getSingers();
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.country)) {
							music.setCountry(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.language)) {
							music.setLanguage(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							music.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.length)) {
							music.setLength(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							music.setImage(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.stars)) {
							music.setStars(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.count)) {
							music.setCount(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.albuminfo)) {
							music.setAlbumInfo(parser.nextText());
						}
					}
					if (level == 3) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singer = new SingerInfoStructure();
							level = 4;
						}
					}
					if (level == 4) {
						if (parser.getName().equals(
								InfoFunctionInterface.singerid)) {
							singer.setSingerId(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							singer.setName(parser.nextText());
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							singer.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.nation)) {
							singer.setNation(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							singer.setImage(parser.nextText());
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singers.add(singer);
						level = 3;
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						level = 2;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						musics.add(music);
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.musics, musics);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_AlbumFilterFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<AlbumInfoStructure> albums = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			AlbumInfoStructure album = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;
			Boolean isMusicsLevel = true;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						albums = new ArrayList<AlbumInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.album)) {
						album = new AlbumInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.albumid)) {
							album.setAlbumId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							album.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.release)) {
							album.setRelease(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.detail)) {
							album.setDetail(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							album.setImage(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							musics = (ArrayList<MusicInfoStructure>) album
									.getMusics();
							level = 3;
							isMusicsLevel = true;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = album.getSingers();
							level = 3;
							isMusicsLevel = false;
						}
					}
					if (isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.music)) {
								music = new MusicInfoStructure();
								level = 4;
							}
						}

						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.musicid)) {
								music.setMusicId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								music.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = music.getSingers();
								level = 5;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.country)) {
								music.setCountry(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.language)) {
								music.setLanguage(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								music.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.length)) {
								music.setLength(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								music.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.stars)) {
								music.setStars(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.count)) {
								music.setCount(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.albuminfo)) {
								music.setAlbumInfo(parser.nextText());
							}
						}
						if (level == 5) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 6;
							}
						}
						if (level == 6) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}
					if (!isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 4;
							}
						}
						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (!isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 2;
						}
					}
					if (isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 5;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 4;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							musics.add(music);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							level = 2;
						}
					}

					if (parser.getName().equals(InfoFunctionInterface.album)) {
						albums.add(album);
						level = 1;
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.albums, albums);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_for_ActivityFilterFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;
			ArrayList<ActivityInfoStructure> activitys = null;
			ActivityInfoStructure activity = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.activitys)) {
						activitys = new ArrayList<ActivityInfoStructure>();
					}
					if (parser.getName().equals(InfoFunctionInterface.activity)) {
						activity = new ActivityInfoStructure();
					}
					if (parser.getName().equals(
							InfoFunctionInterface.activityid)) {
						activity.setActivityid(Integer.parseInt(parser
								.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.image)) {
						activity.setImage(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.title)) {
						activity.setTitle(parser.nextText());
					}
					if (parser.getName().equals(
							InfoFunctionInterface.description)) {
						activity.setDescription(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.type)) {
						activity.setType(Integer.parseInt(parser.nextText()));
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals(InfoFunctionInterface.activity)) {
						activitys.add(activity);
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.activitys)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.activitys, activitys);
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_MusicVideoFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.path)) {
						result.putString(InfoFunctionInterface.path,
								parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.orginal)) {
						result.putString(InfoFunctionInterface.orginal,
								parser.nextText());
					}
					if (parser.getName().equals(
							InfoFunctionInterface.accompaniment)) {
						result.putString(InfoFunctionInterface.accompaniment,
								parser.nextText());
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.videotype)) {
						result.putString(InfoFunctionInterface.videotype,
								parser.nextText());
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.audiotype)) {
						result.putString(InfoFunctionInterface.audiotype,
								parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.backup)) {
						result.putString(InfoFunctionInterface.backup,
								parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_MusicFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			List<SingerInfoStructure> singers = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			Boolean isInSinger = false;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						music = new MusicInfoStructure();
					}

					if (parser.getName().equals(InfoFunctionInterface.musicid)) {
						music.setMusicId(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.name)) {
						music.setName(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						singers = music.getSingers();
					}
					if (parser.getName().equals(InfoFunctionInterface.country)) {
						music.setCountry(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.language)) {
						music.setLanguage(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.type)) {
						music.setType(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.length)) {
						music.setLength(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.image)) {
						music.setImage(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.stars)) {
						music.setStars(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.count)) {
						music.setCount(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.albuminfo)) {
						music.setAlbumInfo(parser.nextText());
					}

					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singer = new SingerInfoStructure();
						isInSinger = true;
					}

					if (isInSinger) {
						if (parser.getName().equals(
								InfoFunctionInterface.singerid)) {
							singer.setSingerId(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							singer.setName(parser.nextText());
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							singer.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.nation)) {
							singer.setNation(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							singer.setImage(parser.nextText());
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singers.add(singer);
						isInSinger = false;
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						result.putParcelable(InfoFunctionInterface.music, music);
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_AlbumFunction(String contentData) {
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			AlbumInfoStructure album = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;
			Boolean isMusicsLevel = true;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.album)) {
						album = new AlbumInfoStructure();
						level = 1;
					}
					if (level == 1) {
						if (parser.getName().equals(
								InfoFunctionInterface.albumid)) {
							album.setAlbumId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							album.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.release)) {
							album.setRelease(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.detail)) {
							album.setDetail(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							album.setImage(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							musics = (ArrayList<MusicInfoStructure>) album
									.getMusics();
							level = 2;
							isMusicsLevel = true;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = album.getSingers();
							level = 2;
							isMusicsLevel = false;
						}
					}
					if (isMusicsLevel) {
						if (level == 2) {
							if (parser.getName().equals(
									InfoFunctionInterface.music)) {
								music = new MusicInfoStructure();
								level = 3;
							}
						}

						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.musicid)) {
								music.setMusicId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								music.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = music.getSingers();
								level = 4;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.country)) {
								music.setCountry(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.language)) {
								music.setLanguage(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								music.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.length)) {
								music.setLength(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								music.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.stars)) {
								music.setStars(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.count)) {
								music.setCount(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.albuminfo)) {
								music.setAlbumInfo(parser.nextText());
							}
						}
						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 5;
							}
						}
						if (level == 5) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}
					if (!isMusicsLevel) {
						if (level == 2) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 3;
							}
						}
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (!isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 2;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 1;
						}
					}
					if (isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 4;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 3;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							musics.add(music);
							level = 2;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							level = 1;
						}
					}

					if (parser.getName().equals(InfoFunctionInterface.album)) {
						result.putParcelable(InfoFunctionInterface.album, album);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_SingerFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;
			SingerInfoStructure singer = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singer = new SingerInfoStructure();
					}
					if (parser.getName().equals(
							InfoFunctionInterface.singerid)) {
						singer.setSingerId(Integer.parseInt(parser
								.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.name)) {
						singer.setName(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.type)) {
						singer.setType(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(
							InfoFunctionInterface.nation)) {
						singer.setNation(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.image)) {
						singer.setImage(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						result.putParcelable(InfoFunctionInterface.singer, singer);
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Bundle parseInfoXml_For_SingerMusicFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						musics = new ArrayList<MusicInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						music = new MusicInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.musicid)) {
							music.setMusicId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							music.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = music.getSingers();
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.country)) {
							music.setCountry(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.language)) {
							music.setLanguage(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							music.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.length)) {
							music.setLength(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							music.setImage(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.stars)) {
							music.setStars(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.count)) {
							music.setCount(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.albuminfo)) {
							music.setAlbumInfo(parser.nextText());
						}
					}
					if (level == 3) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singer = new SingerInfoStructure();
							level = 4;
						}
					}
					if (level == 4) {
						if (parser.getName().equals(
								InfoFunctionInterface.singerid)) {
							singer.setSingerId(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							singer.setName(parser.nextText());
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							singer.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.nation)) {
							singer.setNation(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							singer.setImage(parser.nextText());
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singers.add(singer);
						level = 3;
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						level = 2;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						musics.add(music);
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.musics, musics);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_SingerAlbumFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<AlbumInfoStructure> albums = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			AlbumInfoStructure album = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;
			Boolean isMusicsLevel = true;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						albums = new ArrayList<AlbumInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.album)) {
						album = new AlbumInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.albumid)) {
							album.setAlbumId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							album.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.release)) {
							album.setRelease(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.detail)) {
							album.setDetail(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							album.setImage(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							musics = (ArrayList<MusicInfoStructure>) album
									.getMusics();
							level = 3;
							isMusicsLevel = true;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = album.getSingers();
							level = 3;
							isMusicsLevel = false;
						}
					}
					if (isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.music)) {
								music = new MusicInfoStructure();
								level = 4;
							}
						}

						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.musicid)) {
								music.setMusicId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								music.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = music.getSingers();
								level = 5;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.country)) {
								music.setCountry(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.language)) {
								music.setLanguage(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								music.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.length)) {
								music.setLength(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								music.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.stars)) {
								music.setStars(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.count)) {
								music.setCount(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.albuminfo)) {
								music.setAlbumInfo(parser.nextText());
							}
						}
						if (level == 5) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 6;
							}
						}
						if (level == 6) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}
					if (!isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 4;
							}
						}
						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (!isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 2;
						}
					}
					if (isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 5;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 4;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							musics.add(music);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							level = 2;
						}
					}

					if (parser.getName().equals(InfoFunctionInterface.album)) {
						albums.add(album);
						level = 1;
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.albums, albums);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Bundle parseInfoXml_For_SearchSuggestFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;
			ArrayList<String> words = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.words)) {
						words = new ArrayList<String>();
					}
					if (parser.getName().equals(InfoFunctionInterface.word)) {
						words.add(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals(InfoFunctionInterface.words)) {
						result.putStringArrayList(InfoFunctionInterface.words, words);
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Bundle parseInfoXml_For_SearchMusicFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						musics = new ArrayList<MusicInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						music = new MusicInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.musicid)) {
							music.setMusicId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							music.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = music.getSingers();
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.country)) {
							music.setCountry(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.language)) {
							music.setLanguage(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							music.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.length)) {
							music.setLength(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							music.setImage(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.stars)) {
							music.setStars(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.count)) {
							music.setCount(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.albuminfo)) {
							music.setAlbumInfo(parser.nextText());
						}
					}
					if (level == 3) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singer = new SingerInfoStructure();
							level = 4;
						}
					}
					if (level == 4) {
						if (parser.getName().equals(
								InfoFunctionInterface.singerid)) {
							singer.setSingerId(Integer.parseInt(parser
									.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							singer.setName(parser.nextText());
						}
						if (parser.getName().equals(InfoFunctionInterface.type)) {
							singer.setType(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(
								InfoFunctionInterface.nation)) {
							singer.setNation(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							singer.setImage(parser.nextText());
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singers.add(singer);
						level = 3;
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						level = 2;
					}
					if (parser.getName().equals(InfoFunctionInterface.music)) {
						musics.add(music);
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.musics)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.musics, musics);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Bundle parseInfoXml_For_SearchAlbumFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();

			Bundle result = null;
			ArrayList<AlbumInfoStructure> albums = null;
			ArrayList<MusicInfoStructure> musics = null;
			List<SingerInfoStructure> singers = null;
			AlbumInfoStructure album = null;
			MusicInfoStructure music = null;
			SingerInfoStructure singer = null;

			int level = 0;
			Boolean isMusicsLevel = true;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						albums = new ArrayList<AlbumInfoStructure>();
						level = 1;
					}
					if (parser.getName().equals(InfoFunctionInterface.album)) {
						album = new AlbumInfoStructure();
						level = 2;
					}
					if (level == 2) {
						if (parser.getName().equals(
								InfoFunctionInterface.albumid)) {
							album.setAlbumId(Integer.parseInt(parser.nextText()));
						}
						if (parser.getName().equals(InfoFunctionInterface.name)) {
							album.setName(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.release)) {
							album.setRelease(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.detail)) {
							album.setDetail(parser.nextText());
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.image)) {
							album.setImage(parser.nextText());
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							musics = (ArrayList<MusicInfoStructure>) album
									.getMusics();
							level = 3;
							isMusicsLevel = true;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							singers = album.getSingers();
							level = 3;
							isMusicsLevel = false;
						}
					}
					if (isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.music)) {
								music = new MusicInfoStructure();
								level = 4;
							}
						}

						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.musicid)) {
								music.setMusicId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								music.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.singers)) {
								singers = music.getSingers();
								level = 5;
							}
							if (parser.getName().equals(
									InfoFunctionInterface.country)) {
								music.setCountry(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.language)) {
								music.setLanguage(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								music.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.length)) {
								music.setLength(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								music.setImage(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.stars)) {
								music.setStars(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.count)) {
								music.setCount(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.albuminfo)) {
								music.setAlbumInfo(parser.nextText());
							}
						}
						if (level == 5) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 6;
							}
						}
						if (level == 6) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}
					if (!isMusicsLevel) {
						if (level == 3) {
							if (parser.getName().equals(
									InfoFunctionInterface.singer)) {
								singer = new SingerInfoStructure();
								level = 4;
							}
						}
						if (level == 4) {
							if (parser.getName().equals(
									InfoFunctionInterface.singerid)) {
								singer.setSingerId(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.name)) {
								singer.setName(parser.nextText());
							}
							if (parser.getName().equals(
									InfoFunctionInterface.type)) {
								singer.setType(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.nation)) {
								singer.setNation(Integer.parseInt(parser
										.nextText()));
							}
							if (parser.getName().equals(
									InfoFunctionInterface.image)) {
								singer.setImage(parser.nextText());
							}
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (!isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 2;
						}
					}
					if (isMusicsLevel) {
						if (parser.getName().equals(
								InfoFunctionInterface.singer)) {
							singers.add(singer);
							level = 5;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.singers)) {
							level = 4;
						}
						if (parser.getName()
								.equals(InfoFunctionInterface.music)) {
							musics.add(music);
							level = 3;
						}
						if (parser.getName().equals(
								InfoFunctionInterface.musics)) {
							level = 2;
						}
					}

					if (parser.getName().equals(InfoFunctionInterface.album)) {
						albums.add(album);
						level = 1;
					}

					if (parser.getName().equals(InfoFunctionInterface.albums)) {
						result.putParcelableArrayList(
								InfoFunctionInterface.albums, albums);
						level = 0;
					}

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Bundle parseInfoXml_For_SearchSingerFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;
			SingerInfoStructure singer = null;
			ArrayList<SingerInfoStructure> singers = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.total)) {
						result.putInt(InfoFunctionInterface.total,
								Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.page)) {
						result.putInt(InfoFunctionInterface.page,
								Integer.parseInt(parser.nextText()));
					}
					
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						singers = new ArrayList<SingerInfoStructure>();
					}
					
					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singer = new SingerInfoStructure();
					}
					if (parser.getName().equals(
							InfoFunctionInterface.singerid)) {
						singer.setSingerId(Integer.parseInt(parser
								.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.name)) {
						singer.setName(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.type)) {
						singer.setType(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(
							InfoFunctionInterface.nation)) {
						singer.setNation(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName()
							.equals(InfoFunctionInterface.image)) {
						singer.setImage(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals(InfoFunctionInterface.singer)) {
						singers.add(singer);
					}
					if (parser.getName().equals(InfoFunctionInterface.singers)) {
						result.putParcelableArrayList(InfoFunctionInterface.singers, singers);
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Bundle parseInfoXml_For_UserInfoFunction(String contentData)
	{
		try {
			parser.setInput(new StringReader(contentData));
			int eventType = parser.getEventType();
			Bundle result = null;
			UserInfoStructure user = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					result = new Bundle();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals(InfoFunctionInterface.user)) {
						user = new UserInfoStructure();
					}
					
					if (parser.getName().equals(InfoFunctionInterface.uid)) {
						user.setUid(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.name)) {
						user.setName(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.dob)) {
						user.setDob(parser.nextText());
					}
					if (parser.getName().equals(InfoFunctionInterface.gender)) {
						user.setGender(Integer.parseInt(parser.nextText()));
					}
					if (parser.getName().equals(InfoFunctionInterface.reg)) {
						user.setReg(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals(InfoFunctionInterface.user)) {
						result.putParcelable(InfoFunctionInterface.user, user);
					}
					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = parser.next();
			}

			return result;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String writeInfoXml_For_SystemApkFunction(Bundle info) {
		StringWriter writer = new StringWriter();

		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", InfoFunctionInterface.boxid);
			serializer.text(String.valueOf(info
					.getInt(InfoFunctionInterface.boxid)));
			serializer.endTag("", InfoFunctionInterface.boxid);
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String writeInfoXml_For_MusicListFunction(Bundle info) {
		StringWriter writer = new StringWriter();

		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag("", InfoFunctionInterface.name);
			serializer.text(String.valueOf(info
					.getInt(InfoFunctionInterface.name)));
			serializer.endTag("", InfoFunctionInterface.name);

			serializer.startTag("", InfoFunctionInterface.returntype);
			serializer.text(String.valueOf(info
					.getInt(InfoFunctionInterface.returntype)));
			serializer.endTag("", InfoFunctionInterface.returntype);

			serializer.startTag("", InfoFunctionInterface.number);
			serializer.text(String.valueOf(info
					.getInt(InfoFunctionInterface.number)));
			serializer.endTag("", InfoFunctionInterface.number);

			serializer.startTag("", InfoFunctionInterface.page);
			serializer.text(String.valueOf(info
					.getInt(InfoFunctionInterface.page)));
			serializer.endTag("", InfoFunctionInterface.page);

			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
